public class IllegalDukeDescriptionException extends Exception {
    public IllegalDukeDescriptionException(String command) {
        super("☹ OOPS!!! The description of a " + command + " cannot be incomplete.");
    }
}
