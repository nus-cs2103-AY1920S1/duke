public class DukeException extends Exception {
    public DukeException(String message) {
        super("\t  " + ((char) 0x2639) + " OOPS!!! " + message);
    }
}
