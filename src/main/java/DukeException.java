public class DukeException extends Exception {
    private String errMsg;
    public DukeException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return this.errMsg;
    }

}
