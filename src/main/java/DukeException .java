class DukeException  extends Exception {
    String msg;

    public DukeException (String msg) {
        this.msg = msg;
    }

    public String toString() {
        return "OOPS!!! " + msg;
    }
}