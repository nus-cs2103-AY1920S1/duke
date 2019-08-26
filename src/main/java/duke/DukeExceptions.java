package duke;

public class DukeExceptions extends Exception {
    private String displayMsg;

    private DukeExceptions(String message) {
        super(message);
    }

    protected DukeExceptions(String message, String displayMsg) {
        this(message);
        this.displayMsg = displayMsg;
    }

    public String getDisplayMsg() {
        return this.displayMsg;
    }
}
