public class IntFormatException extends DukeException {
    private String type;

    public IntFormatException(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("%s Please add an integer after %s!", super.toString(), type);
    }
}
