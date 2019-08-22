public class TaskException extends Exception{

    public TaskException() {};

    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a Task cannot be empty.";
    }
}
