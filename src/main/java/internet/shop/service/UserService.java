package internet.shop.service;

import internet.shop.exception.ValidationException;
import internet.shop.model.entity.User;
import internet.shop.model.entity.UserStatus;
import internet.shop.model.form.UserForm;
import internet.shop.repository.UserRepository;
import internet.shop.repository.UserStatusRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserStatusRepository userStatusRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserStatusRepository userStatusRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userStatusRepository = userStatusRepository;
        this.passwordEncoder = passwordEncoder;
    }


    private User convertToUser(UserForm userForm){
        User newUser = new User();
        newUser.setName(userForm.getName());
        newUser.setLogin(userForm.getLogin());
        newUser.setPassword(userForm.getPassword());
        return newUser;
    }

    public void addClient(User newUser, BindingResult bindingResult) throws ValidationException {
        ValidationException validationException = new ValidationException();

        validationException.add(bindingResult);

        Optional<User> user = userRepository.findByLogin(newUser.getLogin());
        if (user.isPresent()) {
            validationException.add("login", "Этот логин уже используется");
        }

        validationException.throwIf();

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        if (newUser.getStatus() == null) {
            newUser.setStatus(userStatusRepository.getOne(1L));
        }
        userRepository.save(newUser);
    }

    public void addClient(UserForm userForm, BindingResult bindingResult) throws ValidationException {
        ValidationException validationException = new ValidationException();

        validationException.add(bindingResult);

        if(!(userForm.getPassword().equals(userForm.getPasswordConfirm()))){
            validationException.add("passwordConfirm", "Введенные пароли не совпадает");
        }

        Optional<User> user = userRepository.findByLogin(userForm.getLogin());
        if (user.isPresent()) {
            validationException.add("login", "Этот логин уже используется");
        }

        validationException.throwIf();

        User newUser = convertToUser(userForm);
        UserStatus clientStatus = new UserStatus();
        clientStatus.setId(1L);
        newUser.setStatus(clientStatus);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        userRepository.save(newUser);
    }



    public void deleteOne(Long id) {
        User userRemoved = userRepository.getByIdAndRemovedFalse(id);
        userRemoved.setRemoved(true);
        userRepository.save(userRemoved);

    }

    public void put(Long id, User newUser) throws ObjectNotFoundException {

        if (!userRepository.existsByIdAndRemovedFalse(id)) {
            throw new ObjectNotFoundException(id, "User");
        }
        newUser.setId(id);
        userRepository.save(newUser);
    }

    public User getOne(Long id) throws ObjectNotFoundException {
        User curUser = userRepository.getByIdAndRemovedFalse(id);
        if (curUser == null) {
            throw new ObjectNotFoundException(id, "User");
        }
        return curUser;
    }

    public User getCurrentUser() {
        try {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)
                    SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            return userRepository.getByLoginAndRemovedFalse(user.getUsername());
        } catch (ClassCastException e) {
            return null;
        }


    }
}
