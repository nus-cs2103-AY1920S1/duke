public class DukeException extends Exception{

    protected String type;

    public DukeException(String input) {
        this.type = input;
    }

    @Override
    public String getMessage() {
        switch(this.type) {
        case "invalid number":
            return "Please enter a valid number.";
        case "empty todo":
            return "The description of a todo cannot be empty.";
        case "empty deadline date":
            return "Please enter a date for your deadline in the form '/by <your date here>' without <>.";
        case "empty event date":
            return "Please enter a date for your event in the form '/at <your date here>' without <>.";
        default:
            return "I'm sorry, but I don't know what that means :-(";
        }
    }
}
