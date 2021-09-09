package skill.factory.testtask.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class UserEntity {
    private final int id;
    private final BigDecimal balance;
}
