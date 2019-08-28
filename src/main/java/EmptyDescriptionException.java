public class EmptyDescriptionException extends DukeException {
    private String type;

    public EmptyDescriptionException(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("%s The description of %s cannot be empty!", super.toString(), type);
    }
}
