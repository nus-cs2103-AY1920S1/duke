import java.util.ArrayList;

/**
 * Represents a list of tasks. A <code>TaskList</code> object
 * corresponds to a list of tasks represented by an <code>ArrayList</code>
 * of <code>Task</code> and an integer.
 */
public class TaskList {

    private ArrayList<Task> taskList;
    private int numTask;

    /**
     * Constructs a new <code>TaskList</code>.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
        numTask = 0;
    }

    /**
     * Constructs a <code>TaskList</code> from an existing list.
     *
     * @param taskList Existing list.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        numTask = taskList.size();
    }

    public int getNumTask() {
        return numTask;
    }

    public ArrayList<Task> getList() {
        return taskList;
    }

    /**
     * Returns last <code>Task</code> that was added to the list of tasks.
     *
     * @return Last <code>Task</code>.
     */
    public Task getTask() {
        return taskList.get(numTask - 1);
    }

    /**
     * Returns <code>Task</code> at specified index of the list of tasks.
     *
     * @param i List index.
     * @return <code>Task</code> at index position.
     */
    public Task getTask(int i) {
        return taskList.get(i);
    }

    /**
     * Updates and returns a <code>Task</code> as done based on task number.
     *
     * @param s Task number.
     * @return Done <code>Task</code>.
     */
    public Task doneTask(String s) {
        Task t = taskList.get(Integer.valueOf(s) - 1);
        t.markAsDone();
        return t;
    }

    /**
     * Removes a <code>Task</code> from list of tasks based on task number.
     *
     * @param s Task number.
     * @return Removed <code>Task</code>.
     */
    public Task deleteTask(String s) {
        int listRank = Integer.valueOf(s) - 1;
        Task t = taskList.get(listRank);
        taskList.remove(listRank);
        numTask--;
        return t;
    }

    /**
     * Updates list of task with a new <code>Todo</code>.
     *
     * @param s Task details.
     */
    public void todoTask(String s) {
        taskList.add(new Todo(s));
        numTask++;
    }

    /**
     * Updates list of task with a new <code>Deadline</code>.
     *
     * @param s Task details in array.
     */
    public void deadlineTask(String[] s) {
        taskList.add(new Deadline(s[0], s[1]));
        numTask++;
    }

    /**
     * Updates list of task with a new <code>Event</code>.
     *
     * @param s Task details in array.
     */
    public void eventTask(String[] s) {
        taskList.add(new Event(s[0], s[1]));
        numTask++;
    }
}