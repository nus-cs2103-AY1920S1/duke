public class InvalidIDException extends IllegalArgumentException {
    private String id;
    public InvalidIDException(String msg) {
        super(msg);
        this.id = msg;
    }

    @Override
    public String toString() {
        return String.format("\u2639 AIGOO!! %s is an invalid ID!", this.id);
    }
}
