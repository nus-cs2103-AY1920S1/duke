public class DukeException extends Exception {

    private String s;

    public DukeException(String s) {
        super(s);
        this.s = s;
    }

}
