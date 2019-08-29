public class MissingTodoException extends Exception {

    public String toString() {
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }

}
