package duke.exception;

public class DukeDateFormatException extends Exception {

    private String message;

    public DukeDateFormatException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }

}
