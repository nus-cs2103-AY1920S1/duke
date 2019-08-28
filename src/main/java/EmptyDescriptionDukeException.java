public class EmptyDescriptionDukeException extends DukeException {
    String id;
    String desc = " ";

    public EmptyDescriptionDukeException(String id, String desc) {
        super(id);
        this.id = id;
        this.desc = " " + desc + " ";
    }

    public EmptyDescriptionDukeException(String id) {
        super(id);
        this.id = id;
    }

    @Override
    public String toString() {
        return "OOPS!!! The description" + desc + "of a " + id + " task cannot be empty.";
    }
}
