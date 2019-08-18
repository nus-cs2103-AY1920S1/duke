public class EmptyDescriptionException extends Exception {
    String description;

    public EmptyDescriptionException(String description) {
        super(description);
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
