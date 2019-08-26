public class DukeException extends Exception {

    private String border = "    ____________________________________________________________";
    private String s;

    public DukeException(String s) {
        super(s);
        this.s = s;
    }

    @Override
    public String toString() {
        return border + "\n" + s + "\n" + border;
    }
}
