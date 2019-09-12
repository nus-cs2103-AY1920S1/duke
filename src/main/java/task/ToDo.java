package task;

/**
 * Creates a task without any date/time attached to it.
 */
public class ToDo extends Task {
    ToDo(String desc) {
        super(desc);
    }

    public ToDo(String desc, boolean isDone) {
        super(desc, isDone);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), description);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof ToDo) {
            ToDo otherToDo = (ToDo) o;
            return description.equals(otherToDo.description);
        } else {
            return false;
        }
    }
}
