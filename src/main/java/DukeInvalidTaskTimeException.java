public class DukeInvalidTaskTimeException extends DukeException {

    public DukeInvalidTaskTimeException(String msg) {
        super("Did you remember to use" + (msg.equals("deadline") ? " \"/by\"?" : " \"/at\"?"));
    }
}
