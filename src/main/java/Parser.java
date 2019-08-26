public class Parser {

    public Parser() {};

    protected String action;
    protected Ui ui = new Ui();

    public String parseAction(String input) throws DukeException {
        String[] substrings = input.split(" ");
        String action = substrings[0];

        if (substrings.length == 0) {
            throw new DukeException("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-("); //no valid command given
        } else if (substrings.length == 1) {
            throw new DukeException("    ☹ OOPS!!! The description of a "+ action +" cannot be empty."); //empty description
        }

        return action;
    }

    public String parseToDo(String action, String input) {
        String description = input.replace(action, "");
        return description.trim();
    }

    public String parseDescription(String input, String action) {
        String substring = input.replace(action, "");
        //Split task and deadline
        String[] parts = substring.split("\\/..");
        String description = parts[0].trim(); // Remove blank spaces

        return description;
    }

    public String parseDateTime(String input, String action) {
        String substring = input.replace(action, "");
        //Split task and deadline
        String[] parts = substring.split("\\/..");
        String dateTime = parts[1].trim(); // Remove blank spaces

        return dateTime;
    }


}
