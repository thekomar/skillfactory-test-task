package skill.factory.testtask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import skill.factory.testtask.domain.User;
import skill.factory.testtask.domain.UserException;
import skill.factory.testtask.entity.UserEntity;
import skill.factory.testtask.exceptions.NegateUserBalanceException;
import skill.factory.testtask.exceptions.UserNotFoundException;
import skill.factory.testtask.repo.UserRepository;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BalanceServiceImpl implements BalanceService {
    private final UserRepository userRepository;

    @Override
    public BigDecimal getBalance(int id) {
        return userRepository.findById(id)
                .map(UserEntity::getBalance)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public Optional<User> takeMoney(User user) {
        Optional<UserEntity> userOptionalFromDbById = userRepository.findById(user.getId());
        User userFromDb = userOptionalFromDbById
                .map(userEntity -> new User(userEntity.getId(), userEntity.getBalance()))
                .orElseThrow(() -> new UserNotFoundException(user.getId()));
        if (userFromDb.getBalance().compareTo(user.getBalance()) < 0) {
            throw new NegateUserBalanceException(new UserException(userFromDb.getId(), userFromDb.getBalance(), user.getBalance()));
        } else {
            BigDecimal resultBalance = userFromDb.getBalance().subtract(user.getBalance());
            return userRepository.updateBalance(new UserEntity(userFromDb.getId(), resultBalance))
                    .map(userEntity -> new User(userEntity.getId(), userEntity.getBalance()));
        }
    }


    @Override
    public Optional<User> putMoney(User user) {
        Optional<UserEntity> userOptionalFromDbById = userRepository.findById(user.getId());
        User userFromDb = userOptionalFromDbById
                .map(userEntity -> new User(userEntity.getId(), userEntity.getBalance()))
                .orElseThrow(() -> new UserNotFoundException(user.getId()));
        BigDecimal resultBalance = userFromDb.getBalance().add(user.getBalance());
        return userRepository.updateBalance(new UserEntity(userFromDb.getId(), resultBalance))
                .map(userEntity -> new User(userEntity.getId(), userEntity.getBalance()));
    }
}
