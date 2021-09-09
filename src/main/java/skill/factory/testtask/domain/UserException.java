package skill.factory.testtask.domain;


import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class UserException extends User{
    private final BigDecimal negateBalance;
    public UserException(int id, BigDecimal balance, BigDecimal amountToTransfer) {
        super(id, balance);
        this.negateBalance = balance.subtract(amountToTransfer).abs();
    }

}
