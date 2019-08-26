import java.util.LinkedList;

class TaskList {
    public final LinkedList<Task> allTasks;

    /**
     * Initialises a TaskList with allTasks.
     *
     * @param allTasks The LinkedList containing all existing tasks.
     */
    public TaskList(LinkedList<Task> allTasks) {
        this.allTasks = allTasks;
    }

    /**
     * Initialises an empty TaskList.
     */
    public TaskList() {
        this.allTasks = new LinkedList<>();
    }

}
