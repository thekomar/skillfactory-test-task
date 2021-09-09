package skill.factory.testtask.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class User {
    private final int id;
    private final BigDecimal balance;

    public User(int id) {
        this.id = id;
        this.balance = BigDecimal.ZERO;
    }
}
