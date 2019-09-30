package main;

public class DukeException extends Exception {
    /**
     * DukeException for invalid commands.
     * @param msg String to be passed when exception is thrown.
     */
    public DukeException(String msg) {
        super(msg);
    }
}
