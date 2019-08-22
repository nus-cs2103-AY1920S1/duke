package error;

public class EmptyTodoException extends TaskException {

    @Override
    public String getTaskErrorMessage() {
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }
}
