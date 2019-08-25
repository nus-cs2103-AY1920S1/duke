public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        String errMessage = "\t " + this.getMessage();

        return errMessage;
    }
}
