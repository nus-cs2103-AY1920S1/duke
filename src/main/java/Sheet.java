import java.util.List;

/**
 * Encapsulates a sheet for the list of tasks.
 * Includes operations to add/delete tasks in the list.
 */
public class Sheet {
    private List<Task> tasks;
    private int numOfTask;
    private Ui ui;

    /**
     * Construct a task list.
     *
     * @param tasks List of tasks contained the list.
     */
    public Sheet(List<Task> tasks, Ui ui) {
        this.ui = ui;
        this.tasks = tasks;
        numOfTask = tasks.size();
    }

    public List<Task> getList() {
        return this.tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added to the list.
     */
    public void add(Task task) {
        tasks.add(task);
        ui.showAdd(task.toString().trim());
        this.numOfTask++;
        ui.showCount(numOfTask);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index Serial index of the task in list.
     */
    public void delete(int index) {
        Task removed = tasks.remove(index - 1);
        ui.showRemove(removed.toString().trim());
        this.numOfTask--;
        ui.showCount(numOfTask);
    }

    /**
     * Checks whether the task list is empty.
     *
     * @return True if the task list is empty.
     */
    public boolean isEmpty() {
        return this.tasks.size() == 0;
    }

    /**
     * Marks the task as done.
     *
     * @param index Serial index of the task in list.
     */
    public void markAsDone(int index) {
        Task doneTask = tasks.get(index - 1).finish();
        tasks.set(index - 1, doneTask);
        ui.showDone(doneTask.toString().trim());
    }

    /**
     * Outputs the number of tasks in the list.
     *
     * @return int Number of tasks in the list.
     */
    public int getNumOfTask() {
        return numOfTask;
    }

    /**
     * Outputs the task list.
     *
     */
    public void showList() {
        ui.showListHeader();

        for (int i = 0; i < numOfTask; i++) {
            ui.showTask(i + 1, tasks.get(i).toString().trim());
        }
    }

    /**
     * Remove all tasks from the task list.
     */
    public void clearList() {
        ui.showClearList();
        tasks.clear();
        numOfTask = 0;
    }

    /**
     * Searches for tasks containing the keyword.
     *
     * @param keyword Keyword for searching.
     */
    public void find(String keyword) {
        ui.showSearchHeader();
        int count = 0;
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                count++;
                ui.showTask(count, task.toString().trim());
            }
        }
        if (count == 0) {
            ui.showNotFound();
        }
    }

    /**
     * ToString method of the sheet object.
     *
     * @return String representation of the sheet of task list.
     */
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < numOfTask; i++) {
            output += (i + 1) + ". " + tasks.get(i).toString();
        }
        return output.trim();
    }

}
