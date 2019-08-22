public class DukeException extends Exception {
    String errorMsg;

    public DukeException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return String.format("☹ OOPS!!! %s", this.errorMsg);
    }
}
