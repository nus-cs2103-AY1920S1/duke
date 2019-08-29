public class IllegalDukeFormatException extends Exception {
    public IllegalDukeFormatException(String command) {
        super("☹ OOPS!!! The format of a " + command + " command is wrong.");
    }
}
