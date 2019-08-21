package main.task;
import java.lang.Exception;

public class InsufficientTaskArgumentException extends Exception {
    public InsufficientTaskArgumentException(String msg) {
        super(msg);
    }
}
