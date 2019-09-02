import java.io.IOException;
import java.util.ArrayList;

/**
 * The TaskList class handles the storage, adding and deleting of
 * tasks in Duke program.
 */

public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Creates a new TaskList object initialised with tasks from
     * the inputted array list of Tasks.
     * @param taskList An array list of tasks with which the TaskList object is initialised.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the an array list which is the TaskList object's task list. 
     * @return The current TaskList object's task list.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Adds a new Task into the task list and writes
     * the changes back into the local file of tasks as well.
     * @param task The task to be added into the task list.
     * @param storage The Storage object handling the read and write from the local file storing the tasks.
     * @throws IOException When the file to be written to is not found or does not exist.
     */
    public void addTask(Task task, Storage storage) throws IOException {
        this.taskList.add(task);
        storage.addTaskToFile(task);
    }

    /**
     * Returns a list of all tasks which have the string 
     * queried as the input present in them.
     * @param query The string being searched for in each task.
     * @return List of all tasks containing the queried string.
     */
    public ArrayList<Task> matchingTasks(String query) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task t : this.taskList) {
            String queryLowerCase = query.toLowerCase();
            String stringUnderCheck = t.toString().toLowerCase();
            if (stringUnderCheck.indexOf(queryLowerCase) != -1) {
                matches.add(t);
            }
        }
        return matches;
    }

    /**
     * Deletes a specific task from the task list and writes
     * the changes back into the local file of tasks as well.
     * @param index The index of the task to be deleted from the array list.
     * @param storage The Storage object handling the read and write from the local file storing the tasks.
     * @throws DukeException When the index specified by the user does not exist.
     * @throws IOException When the file to be written to is not found or does not exist.
     */
    public void deleteTask(int index, Storage storage) throws DukeException, IOException {
        try {
            this.taskList.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The item specified does not exist.");
        }
        System.out.println("Noted. I've removed this task: ");
        System.out.println(taskList.get(index - 1).toString());
        this.taskList.remove(index - 1);
        storage.writeToFile(storage.getFilePath(), taskList);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Sets the status of a particular task in the array list of tasks to 
     * completed thereby signifying that the task has been completed.
     * @param index The index of the task in the array list to be set to completed to be marked as completed.
     * @param storage The Storage object handling the read and write from the local file storing the tasks.
     * @throws DukeException When the index specified by the user does not exist.
     * @throws IOException When the file to be written to is not found or does not exist.
     */
    public void markAsDone(int index, Storage storage) throws DukeException, IOException {
        try {
            taskList.get(index - 1).markAsDone();
            storage.writeToFile(storage.getFilePath(), taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The item specified does not exist.");
        }
    }

}