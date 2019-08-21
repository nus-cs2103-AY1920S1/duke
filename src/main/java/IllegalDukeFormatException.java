public class IllegalDukeFormatException extends Exception {
    public IllegalDukeFormatException(String command, String part) {
        super("☹ OOPS!!! The description of a " + command + " must contains " + part + " .");
    }
}
