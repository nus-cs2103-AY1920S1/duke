import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;

    /**
     * This is a constructor for TaskList
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * This method is used to return the task list.
     *
     * @return ArrayList<Task> the task list
     */
    public ArrayList<Task> getList() {
        return list;
    }


    /**
     * This method is used to add a task to the list.
     *
     * @param taskToAdd the task to add
     * @return Nothing
     */
    public void addTask(Task taskToAdd) {
        list.add(taskToAdd);
    }

    /**
     * This method is used to delete a task to the list.
     *
     * @param taskNumToDelete the task number to delete
     * @return Nothing
     */
    public Task deleteTask(int taskNumToDelete) {
        return list.remove(taskNumToDelete - 1);
    }

    /**
     * This method is used to mark a task as done.
     *
     * @param taskNumToMark the task number to mark
     * @return Nothing
     */
    public void doneTask(int taskNumToMark) {
        Task t = list.get(taskNumToMark - 1);
        t.markAsDone();
    }
}
