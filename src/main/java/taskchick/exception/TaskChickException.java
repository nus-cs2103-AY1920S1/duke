package taskchick.exception;

import java.util.InputMismatchException;

public class TaskChickException extends InputMismatchException {

    public TaskChickException(String message) {
        super(message);
    }

}