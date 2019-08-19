public class DukeException {

    String error;

    public DukeException(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        switch(error) {
            case "delete":
                return "☹ OOPS!!! The index of a delete cannot be empty.";
            case "deadline":
                return "☹ OOPS!!! The description of a deadline cannot be empty.";
            case "event":
                return "☹ OOPS!!! The description of an event cannot be empty.";
            case "todo":
                return "☹ OOPS!!! The description of a todo cannot be empty.";
            case "instruction":
                return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            default:
        }
        return "";
    }
}
