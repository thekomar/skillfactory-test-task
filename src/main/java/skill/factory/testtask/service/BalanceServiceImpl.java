package skill.factory.testtask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import skill.factory.testtask.domain.User;
import skill.factory.testtask.domain.UserException;
import skill.factory.testtask.entity.UserEntity;
import skill.factory.testtask.exceptions.NegateUserBalanceException;
import skill.factory.testtask.exceptions.UserNotFoundException;
import skill.factory.testtask.mapper.UserMapper;
import skill.factory.testtask.repo.UserRepository;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BalanceServiceImpl implements BalanceService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public BigDecimal getBalance(int id) {
        return userRepository.findById(id)
                .map(UserEntity::getBalance)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public Optional<User> takeMoney(User user) {
        User userFromDb = getUserFromDb(user.getId());
        if (userFromDb.getBalance().compareTo(user.getBalance()) >= 0) {
            BigDecimal resultBalance = userFromDb.getBalance().add(user.getBalance());
            return getUserAfterUpdate(new User(userFromDb.getId(), resultBalance));
        } else {
            throw new NegateUserBalanceException(new UserException(userFromDb.getId(), userFromDb.getBalance(), user.getBalance()));
        }
    }


    @Override
    public Optional<User> putMoney(User user) {
        User userFromDb = getUserFromDb(user.getId());
        BigDecimal resultBalance = userFromDb.getBalance().add(user.getBalance());
        return getUserAfterUpdate(new User(userFromDb.getId(), resultBalance));
    }

    private User getUserFromDb(int id) {
        return userRepository.findById(id)
                .map(userMapper::toUser)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    private Optional<User> getUserAfterUpdate(User user) {
        return userRepository.updateBalance(userMapper.toUserEntity(user))
                .map(userMapper::toUser);
    }
}
