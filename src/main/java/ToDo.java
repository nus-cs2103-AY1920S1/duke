public class ToDo extends Task{
    public ToDo(String name) throws EmptyTaskDukeException {
        super(name);
        if (name == null) {
            throw new EmptyTaskDukeException("todo");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
