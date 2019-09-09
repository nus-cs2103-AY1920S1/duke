package error.task;

public class EmptyTodoException extends TaskCreationException {

    @Override
    public String getDetails() {
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }
}
