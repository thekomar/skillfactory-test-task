package skill.factory.testtask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import skill.factory.testtask.domain.User;
import skill.factory.testtask.exceptions.UserNotFoundException;
import skill.factory.testtask.mapper.UserMapper;
import skill.factory.testtask.repo.UserRepository;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User getUserById(int id) {
        return userRepository.findById(id)
                .map(userMapper::toUser)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User updateUser(User user) {
        return userRepository.update(userMapper.toUserEntity(user))
                .map(userMapper::toUser)
                .orElseThrow(() -> new UserNotFoundException(user.getId()));
    }
}
