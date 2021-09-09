package skill.factory.testtask.controller.model;

import liquibase.pro.packaged.S;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private final int status;
    private final int code;
    private final String message;
}
