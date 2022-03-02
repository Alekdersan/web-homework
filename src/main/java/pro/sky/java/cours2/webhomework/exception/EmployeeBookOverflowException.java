package pro.sky.java.cours2.webhomework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EmployeeBookOverflowException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Employee book is full";

    public EmployeeBookOverflowException() {
        this(DEFAULT_MESSAGE);
    }

    public EmployeeBookOverflowException(String message) {
        super (message);
    }
}
