public class EmptyDescriptionDukeException extends DukeException {
    String id;
    public EmptyDescriptionDukeException(String id) {
        super(id);
        this.id = id;
    }

    @Override
    public String toString() {
        return "OOPS!!! The description of a " + id + " task cannot be empty.";
    }
}
