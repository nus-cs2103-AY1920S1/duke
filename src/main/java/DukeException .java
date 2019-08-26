class DukeException  extends Exception {
    String msg;

    public DukeException (String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return "OOPS!!! " + msg;
    }
}