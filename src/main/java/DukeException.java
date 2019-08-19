public class DukeException extends Exception {
    String description;

    public DukeException(String description) {
        this.description = description;
    }

    public String toString() {
        return this.description;
    }
}
