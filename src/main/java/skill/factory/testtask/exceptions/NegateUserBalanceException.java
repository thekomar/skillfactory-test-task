package skill.factory.testtask.exceptions;

import skill.factory.testtask.domain.User;
import skill.factory.testtask.domain.UserException;

public class NegateUserBalanceException extends RuntimeException{
    private final UserException user;

    public NegateUserBalanceException(UserException user) {
        this.user = user;
    }

    @Override
    public String getMessage() {
        return String.format(
                "У полльзователя с id %s balance = %s не хватает денег на перевод%s",
                user.getId(),
                user.getBalance(),
                user.getNegateBalance());
    }
}
