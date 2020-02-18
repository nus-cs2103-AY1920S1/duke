package duke.exception;

public class MissingTodoException extends DukeException {

    public String toString() {
        return "OOPS!!! The description of a todo cannot be empty.";
    }

}
