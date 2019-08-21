public class DukeException extends Exception{

    private String s;
    public DukeException(){

    }
    public DukeException(String s) {
    super(s);
    this.s = s;
    }

    @Override
    public String toString() {
        return Formatter.LINE + Formatter.INDENT + s.toString() + "\n" + Formatter.LINE;
    }
}
