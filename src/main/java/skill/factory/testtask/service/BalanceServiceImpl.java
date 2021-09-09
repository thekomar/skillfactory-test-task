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
    private final UserService userService;

    @Override
    public BigDecimal getBalance(int id) {
        return userService.getUserById(id)
                .getBalance();
    }

    @Override
    public User takeMoney(User user) {
        User userFromDb = userService.getUserById(user.getId());
        if (userFromDb.getBalance().compareTo(user.getBalance()) >= 0) {
            BigDecimal resultBalance = userFromDb.getBalance().add(user.getBalance());
            return userService.updateUser(new User(userFromDb.getId(), resultBalance));
        } else {
            throw new NegateUserBalanceException(new UserException(userFromDb.getId(), userFromDb.getBalance(), user.getBalance()));
        }
    }


    @Override
    public User putMoney(User user) {
        User userFromDb = userService.getUserById(user.getId());
        BigDecimal resultBalance = userFromDb.getBalance().add(user.getBalance());
        return userService.updateUser(new User(userFromDb.getId(), resultBalance));
    }


}
