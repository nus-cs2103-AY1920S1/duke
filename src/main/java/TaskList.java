import java.util.ArrayList;

/**
 * Represents a list of tasks. A <code>TaskList</code> object
 * corresponds to a list of tasks represented by an <code>ArrayList</code>
 * of <code>Task</code> and an integer.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private int numTask;

    /**
     * Constructs a new <code>TaskList</code>.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        numTask = 0;
    }

    /**
     * Constructs a <code>TaskList</code> from an existing list.
     *
     * @param existingTasks Existing list.
     */
    public TaskList(ArrayList<Task> existingTasks) {
        this.tasks = existingTasks;
        numTask = existingTasks.size();
    }

    public int getNumTask() {
        return numTask;
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Returns last <code>Task</code> that was added to the list of tasks.
     *
     * @return Last <code>Task</code>.
     */
    public Task getTask() {
        return tasks.get(numTask - 1);
    }

    /**
     * Returns <code>Task</code> at specified index of the list of tasks.
     *
     * @param i List index.
     * @return <code>Task</code> at index position.
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Updates and returns a <code>Task</code> as done based on task number.
     *
     * @param index Task number.
     * @return Done <code>Task</code>.
     */
    public Task getDoneTask(String index) {
        Task t = tasks.get(Integer.valueOf(index) - 1);
        t.setDone();
        return t;
    }

    /**
     * Updates list of task with a new <code>Todo</code>.
     *
     * @param detail Task details.
     */
    public void addTodoTask(String detail) {
        tasks.add(new Todo(detail));
        numTask++;
    }

    /**
     * Updates list of task with a new <code>Deadline</code>.
     *
     * @param details Task details in array.
     */
    public void addDeadlineTask(String[] details) {
        tasks.add(new Deadline(details[0], details[1]));
        numTask++;
    }

    /**
     * Updates list of task with a new <code>Event</code>.
     *
     * @param details Task details in array.
     */
    public void addEventTask(String[] details) {
        tasks.add(new Event(details[0], details[1]));
        numTask++;
    }

    /**
     * Removes a <code>Task</code> from list of tasks based on task number.
     *
     * @param index Task number.
     * @return Removed <code>Task</code>.
     */
    public Task deleteTask(String index) {
        int listRank = Integer.valueOf(index) - 1;
        Task t = tasks.get(listRank);
        tasks.remove(listRank);
        numTask--;
        return t;
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();

        for (int i = 0; i < numTask; i++) {
            response.append(String.format("%d.%s", i + 1, getTask(i)) + "\n");
        }

        return response.toString();
    }
}