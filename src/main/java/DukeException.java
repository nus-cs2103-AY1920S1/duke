public class DukeException extends Exception {
    private String msg;

    public DukeException(String msg) {
        this.msg = msg;
    }

    public DukeException() {
        this.msg = "";
    }

    public String getMessage() {
        return this.msg;
    }


}