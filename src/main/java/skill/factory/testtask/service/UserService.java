package skill.factory.testtask.service;

import skill.factory.testtask.domain.User;

import java.util.Optional;

public interface UserService {
    User getUserById(int id);

    User updateUser(User user);
}
