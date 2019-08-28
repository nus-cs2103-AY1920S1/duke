public class DukeException extends Exception {
    public DukeException() {
        super("File does not exist");
    }

    public DukeException(String message) {
        System.out.println(String.format("\u2639 OOPS!!! %s\n", message));
    }
}