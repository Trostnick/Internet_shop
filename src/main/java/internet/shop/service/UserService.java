package internet.shop.service;

import internet.shop.constant.USER_STATUS;
import internet.shop.entity.User;
import internet.shop.entity.UserStatus;
import internet.shop.exception.FindByIdException;
import internet.shop.repository.UserRepository;
import internet.shop.repository.UserStatusRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        this.passwordEncoder=passwordEncoder;
    }


    public void add(User newUser) throws IllegalArgumentException{
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
    }

    public void deleteOne(Long id) {
        User userRemoved = userRepository.getByIdAndRemovedFalse(id);
        userRemoved.setRemoved(true);
        userRepository.save(userRemoved);

    }

    public void put(Long id, User newUser)throws ObjectNotFoundException{

        if (!userRepository.existsByIdAndRemovedFalse(id)){
            throw new ObjectNotFoundException(id, "User");
        }
        userRepository.save(newUser);
    }

    public User getOne(Long id)throws ObjectNotFoundException {
        User curUser = userRepository.getByIdAndRemovedFalse(id);
        if (curUser == null){
            throw new ObjectNotFoundException(id, "User");
        }
        return curUser;
    }
}
