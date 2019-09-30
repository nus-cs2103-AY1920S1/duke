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

    /**
     * Displays a String representation of this ToDo to the user.
     * @return a String representation of this ToDo
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), description);
    }

    /**
     * Compares if two ToDos are the same.
     * @param o the other object to be compared with.
     * @return a boolean.
     */
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

    /**
     * Returns a String representation of this ToDo to be written in a save file.
     * @return an encoded String representation of this ToDo
     */
    @Override
    public String toEncodedString() {
        int isDoneStatus = isDone ? 1 : 0;

        return String.format("T : %d : %s", isDoneStatus, description);
    }
}
