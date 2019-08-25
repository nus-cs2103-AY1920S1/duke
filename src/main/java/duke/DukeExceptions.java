package duke;

public class DukeExceptions extends Exception {
    String displayMsg;

    DukeExceptions(String message) {
        super(message);
    }

    DukeExceptions(String message, String displayMsg) {
        this(message);
        this.displayMsg = displayMsg;
    }

    public String getDisplayMsg() {
        return this.displayMsg;
    }
}
