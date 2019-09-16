package duke.exceptions;
/**
 * Represents a DukeException class which handles all the possible
 * custom exceptions that might be needed in the program.
 */

public class DukeException extends Exception {

    protected String exceptionType;

    public DukeException(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public String toString() {
        if (exceptionType.equals("done")) {
            return "The index of a done cannot be empty.";
        } else if (exceptionType.equals("delete")) {
            return "The index of a delete cannot be empty.";
        } else if (exceptionType.equals("todo")) {
            return "The description of a todo cannot be empty.";
        } else if (exceptionType.equals("index")) {
            return "No such task in the list.";
        } else if (exceptionType.equals("deadline")) {
            return "Invalid input format for deadline.";
        } else if (exceptionType.equals("event")) {
            return "Invalid input format for event";
        } else if (exceptionType.equals("find")) {
            return "No such keyword in the list!";
        } else if (exceptionType.equals("index")) {
            return "Unable to do so, the list does have contain such an index";
        } else if (exceptionType.equals("file")) {
            return "Unable to do so, File issue!";
        } else if (exceptionType.equals("duplicate")) {
            return "Duplicated Event Description, please change your event Description to a unique one";
        } else if (exceptionType.equals("TaskNum")) {
            return "Invalid Format! Please input a valid Task Number";
        } else if (exceptionType.equals("indexInt")){
            return "Invalid Format! Please input a valid integer index";
        } else {
            return "Invalid Format! Please Follow the Standard Command lines in the Guide.";
        }
    }
}