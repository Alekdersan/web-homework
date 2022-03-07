package pro.sky.java.cours2.webhomework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "Employee not found.";

    public EmployeeNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }

    public EmployeeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeNotFoundException(Throwable cause) {
        super(cause);
    }
}























/*@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Employee not found.";

    public EmployeeNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }

    public EmployeeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeNotFoundException(Throwable cause) {
        super(cause);
    }
}*/
