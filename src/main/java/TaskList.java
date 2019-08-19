import java.util.ArrayList;

/**
 * TaskList class. Instances represents lists of texts stored by user.
 */
public class TaskList {
    /** ArrayList to store Tasks. */
    private ArrayList<Task> list;

    /**
     * Constructor.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Adds Task to list.
     * @param description Description of the task..
     */
    void addTask(String description) {
        list.add(new Task(description));
    }

    /**
     * Does Task at the given 1-based index.
     * @param index index of task to check (1-based).
     */
    void doTask(int index) {
        list.get(index - 1).markAsDone();
    }

    /**
     * Gets a Task from the list.
     * @param index index of task to get (1-based).
     * @return Task object.
     */
    Task getTask(int index) {
        return list.get(index - 1);
    }

    /**
     * String representation of list (indented).
     * @return String representation.
     */
    @Override
    public String toString() {
        String indent = "     ";
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < list.size(); ++i) {
            strb.append(indent).append(i + 1).append(". ").append(list.get(i));
            if (i < list.size() - 1) {
                strb.append("\n");
            }
        }
        return strb.toString();
    }
}
