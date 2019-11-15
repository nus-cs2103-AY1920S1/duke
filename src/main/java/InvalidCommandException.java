public class InvalidCommandException extends Exception {
    String description;

    public InvalidCommandException(String description) {
        super(description);
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
