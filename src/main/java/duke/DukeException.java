package duke;

<<<<<<< .merge_file_a01944
public class DukeException extends Exception{
    private String message = "\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    DukeException() {}

=======
/**
 * Specifies duke-defined exceptions for invalid commands.
 */
public class DukeException extends Exception{
    private String message = "\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    /**
     * Constructs DukeException object for invalid commands.
     */
    DukeException() {}


    /**
     * Constructs DukeException object for insufficient information for specified commands
     * @param message Specified command.
     */
>>>>>>> .merge_file_a15676
    DukeException(String message) {
        this.message = "\t☹ OOPS!!! The description of a " + message + " cannot be empty.";
    }

<<<<<<< .merge_file_a01944
=======
    /**
     * Returns the command that is causing an error.
     * @return Specified command.
     */
>>>>>>> .merge_file_a15676
    public String getMessage() {
        return message;
    }
}
