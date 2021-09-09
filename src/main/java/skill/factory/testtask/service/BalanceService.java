package skill.factory.testtask.service;

import skill.factory.testtask.domain.User;

import java.math.BigDecimal;

public interface BalanceService {
    BigDecimal getBalance(int id);

    User takeMoney(User user);

    User putMoney(User user);
}
