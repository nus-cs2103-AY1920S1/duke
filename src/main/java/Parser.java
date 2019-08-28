public class Parser {

    public Parser() {
    }

    /**
     * Parse the input to return the user action as a String.
     * Ensure that the input has a valid action.
     * If invalid, throw DukeException, indicate action is not recognised.
     * If action is valid but description is blank, ask for description.
     *
     * @param input Entire input command from the user
     * @return action Return the action command of the user
     * @throws DukeException If action or input is invalid
     */
    public String parseAction(String input) throws DukeException {
        String[] substrings = input.split(" ");
        String action = substrings[0];

        if (substrings.length == 0) {
            throw new DukeException("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-("); //no valid command given
        } else if (substrings.length == 1) {
            throw new DukeException("    ☹ OOPS!!! The description of a " + action + " cannot be empty."); //empty description
        }

        return action;
    }

    /**
     * Parse the description from the input as a String.
     * For Event or Deadline Tasks, important to split substrings into
     * description and date/time, while for Todo Tasks and "find" command not required.
     *
     * @param input  The entire user input with action and task full description
     * @param action The designated user action,
     * @return description The description of the Event or Deadline task, without action and dateTime
     * @return substring The description of the to do task, without action and dateTime
     */
    public String parseDescription(String action, String input) {
        String substring = input.replace(action, "");
        //Split task and date or time
        String[] parts = substring.split("\\/..");
        String description;
        if (action.equals("todo") || action.equals("find"))
            return substring; //no date or time
        else
            description = parts[0].trim(); // Remove blank spaces

        return description;
    }

    /**
     * Parse the date or time as a String
     *
     * @param input  The entire user input with action and task full description
     * @param action The designated user action,
     * @return dateTime The date or time as a String
     */
    public String parseDateTime(String action, String input) {
        String substring = input.replace(action, "");
        //Split task and date time
        String[] parts = substring.split("\\/..");
        String dateTime = parts[1].trim(); // Remove blank spaces

        return dateTime;
    }


}
