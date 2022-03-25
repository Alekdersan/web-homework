package pro.sky.java.cours2.webhomework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidNamesException extends Exception {
    public InvalidNamesException(String name) {
        super ("Недопустимое имя: " + name);
    }
}
