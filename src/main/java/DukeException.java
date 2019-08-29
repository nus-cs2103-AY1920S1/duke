public class DukeException extends Exception{

    private String message;

    public DukeException(String msg) {
    super(msg);
    this.message = msg;
    }

    @Override
    public String toString() {
        return message;
    }
}
