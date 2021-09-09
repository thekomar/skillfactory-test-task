package skill.factory.testtask.exceptions;

public class UserNotFoundException extends RuntimeException{
    private final int userId;

    public UserNotFoundException(int userId) {
        this.userId = userId;
    }

    @Override
    public String getMessage() {
        return "Пользователь с таким id не найден: " + userId;
    }
}
