package internet.shop.service;

import internet.shop.constant.USER_STATUS;
import internet.shop.entity.User;
import internet.shop.entity.UserStatus;
import internet.shop.exception.FindByIdException;
import internet.shop.repository.UserRepository;
import internet.shop.repository.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserStatusRepository userStatusRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserStatusRepository userStatusRepository) {
        this.userRepository = userRepository;
        this.userStatusRepository = userStatusRepository;

    }

    private User findOne(Long id) throws FindByIdException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new FindByIdException("User not found");
        }
        User curUser = user.get();
        if (curUser.getStatus().getId().equals(USER_STATUS.REMOVED.getValue())){
            throw new FindByIdException("User was removed");
        }
        return curUser;
    }

    private void addParamsParser(String params, User user) {
        for (String param : params.split(",")) {
            String[] current = param.split("=");

            switch (current[0].replaceAll(" ", "")) {
                case "name":
                    user.setName(current[1]);
                    break;
                case "login":
                    user.setLogin(current[1]);
                    break;
                case "password":
                    user.setPassword(current[1]);
                    break;

            }
        }
    }

    public void add(String params) {
        User newUser = new User();
        UserStatus clientStatus = userStatusRepository.findById(USER_STATUS.CLIENT.getValue()).get();
        newUser.setStatus(clientStatus);
        addParamsParser(params, newUser);
        userRepository.save(newUser);
    }

    public void deleteOne(Long id) {
        User userRemoved = findOne(id);
        UserStatus removedStatus = userStatusRepository.findById(USER_STATUS.REMOVED.getValue()).get();
        userRemoved.setStatus(removedStatus);
        userRepository.save(userRemoved);
    }

    public void put(Long id, String params){
        User curUser=findOne(id);
        addParamsParser(params, curUser);
        userRepository.save(curUser);
    }

    public User getOne(Long id){
        return findOne(id);
    }
}
