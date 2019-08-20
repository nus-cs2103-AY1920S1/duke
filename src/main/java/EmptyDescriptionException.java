public class EmptyDescriptionException extends IllegalArgumentException {
    private String type;
    public EmptyDescriptionException(String msg) {
        super(msg);
        this.type = msg;
    }

    @Override
    public String toString() {
        return String.format("\u2639 OOPS!!! The description of %s cannot be empty.",
                this.type);
    }
}
