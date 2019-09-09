package error.task;

public class InvalidArgumentsException extends TaskCreationException {
    private String invalidArguments;

    public InvalidArgumentsException(String invalidArguments) {
        this.invalidArguments = invalidArguments;
    }

    @Override
    public String getDetails() {
        return "☹ OOPS!!! I'm sorry! Did you mean " + invalidArguments + " :-(";
    }
}
