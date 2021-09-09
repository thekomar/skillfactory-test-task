package skill.factory.testtask.service;

import skill.factory.testtask.domain.User;

import java.math.BigDecimal;
import java.util.Optional;

public interface BalanceService {
    BigDecimal getBalance(int id);

    Optional<User> takeMoney(User user);

    Optional<User> putMoney(User user);
}
