public class DukeException extends Exception{

    protected String type;

    public DukeException(String input) {
        this.type = input.split(" ")[0];
    }

    @Override
    public String getMessage() {
        if (this.type.equals("todo")) {
            return "The description of a todo cannot be empty.";
        } else if (this.type.equals("deadline")) {
            return "The description of a deadline cannot be empty.";
        } else if (this.type.equals("event")) {
            return "The description of a event cannot be empty.";
        } else {
            return "I'm sorry, but I don't know what that means :-(";
        }
    }
}
