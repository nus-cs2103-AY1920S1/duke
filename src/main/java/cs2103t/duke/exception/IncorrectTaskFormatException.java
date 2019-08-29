package cs2103t.duke.exception;

public class IncorrectTaskFormatException extends DukeException {
    private String keyword2;
    public IncorrectTaskFormatException(String msg) {
        super(msg);
        this.keyword2 = msg;
    }

    @Override
    public String toString() {
        return String.format("\u2639 Aish try again with the correct format: <descr> /%s <dateinfo>",
                this.keyword2);
    }
}
