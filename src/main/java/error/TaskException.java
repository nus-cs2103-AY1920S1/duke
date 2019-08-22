package error;

public abstract class TaskException extends RuntimeException {
    public abstract String getTaskErrorMessage();
}
