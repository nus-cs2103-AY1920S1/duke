package error.task;

public class InvalidArgumentsException extends TaskCreationException {
    private String invalidArguments;

    public InvalidArgumentsException(String invalidArguments) {
        this.invalidArguments = invalidArguments;
    }
}
