public class DukeException extends Exception {
    private String e;

    DukeException(String e) {
        super(e);
        this.e = e;
    }

    @Override
    public String toString() {
        return this.e;
    }
}
