import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Encapsulates a sheet for the list of tasks.
 * Includes operations to add/delete tasks in the list.
 */
public class Sheet {
    private List<Task> tasks;
    private Storage storage = new Storage(myPaths.TASK_LIST);
    private int numOfTask;
    private Ui ui = new Ui();

    /**
     * Construct a task list.
     *
     * @param tasks List of tasks contained the list.
     */
    public Sheet(List<Task> tasks) {
        this.tasks = tasks;
        numOfTask = tasks.size();
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added to the list.
     * @throws IOException If errors occurs when accessing the file that contains the list.
     */
    public void add(Task task) throws IOException{
        tasks.add(task);
        storage.save(tasks);
        ui.showAdd(task.toString().trim());
        this.numOfTask++;
        ui.showCount(numOfTask);
        ui.showLine();
    }

    /**
     * Deletes a task from the list.
     *
     * @param index Serial index of the task in list.
     * @throws IOException If errors occurs when accessing the file that contains the list.
     */
    public void delete(int index) throws IOException{
        Task removed = tasks.remove(index - 1);
        storage.save(tasks);
        ui.showRemove(removed.toString().trim());
        this.numOfTask--;
        ui.showCount(numOfTask);
        ui.showLine();
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
     * @throws IOException If errors occurs when accessing the file that contains the list.
     */
    public void markAsDone(int index) throws IOException {
        Task doneTask = tasks.get(index - 1).finish();
        tasks.set(index - 1, doneTask);
        storage.save(tasks);
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
     * @throws FileNotFoundException If the file containing the list is not found.
     */
    public void showList() throws FileNotFoundException{
        ui.showListHeader();

        for (int i = 0; i < numOfTask; i++) {
            ui.showTask(i + 1, tasks.get(i).toString().trim());
        }
        ui.showLine();
    }

    /**
     * Searches for tasks containing the keyword.
     *
     * @param keyword Keyword for searching.
     * @throws IOException If errors occurs when accessing the file that contains the list.
     */
    public void find(String keyword) throws IOException {
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
        ui.showLine();
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
