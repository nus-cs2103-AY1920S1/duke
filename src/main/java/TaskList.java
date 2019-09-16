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

    public int getNumberOfTasks() {
        return this.taskList.size();
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
    public StringBuilder printMatchingTasks(String query) {
        int index = 1;
        StringBuilder sb = new StringBuilder();
        query = query.toLowerCase().replaceAll("\\*", "\\\\w*");
        query = query.contains("\\w") ? ".*" + query + ".*" : query;
        for (Task task : this.taskList) {
            String info = task.toString().toLowerCase();
            if (info.matches(query) || info.contains(query.toLowerCase())) {
                sb.append(index + ". " + task.toString() + "\n");
            }
            index++;
        }
        if (sb.length() == 0) {
            return sb.append("No such tasks found :( " + query);
        } else {
            return sb;
        }
    }

    /**
     * Returns a stringbuilder object with all the tasks in the
     * arraylist of tasks returned in a list form.
     * @return Stringbuilder object with tasks in string form as a list.
     */
    public StringBuilder printTasks() {
        int index = 1;
        StringBuilder sb = new StringBuilder();
        if (this.getTaskList().isEmpty()) {
            return sb.append("You have no tasks.");
        }
        
        sb.append("Here are the tasks in your list:" + "\n");
        for (Task task : this.getTaskList()) {
            sb.append(index + ". " + task.toString());
            sb.append("\n");
            index++;
        }
        assert sb.length() > 0 : "The stringbuilder being returned shouldn't be empty";
        return sb;
    }

    /**
     * Deletes a specific task from the task list and writes
     * the changes back into the local file of tasks as well.
     * @param index The index of the task to be deleted from the array list.
     * @param storage The Storage object handling the read and write from the local file storing the tasks.
     * @return A stringbuilder object containing the response to be given by Duke upon deleting a task successfully.
     * @throws IndexOutOfBoundsException When the index specified by the user does not exist.
     * @throws IOException When the file to be written to is not found or does not exist.
     */
    public StringBuilder deleteTask(int index, Storage storage) throws IOException {
        try {
            this.taskList.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("☹ OOPS!!! The item specified does not exist.");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task: " + "\n");
        sb.append(taskList.get(index - 1).toString() + "\n");
        this.taskList.remove(index - 1);
        storage.writeToFile(storage.getFilePath(), taskList);
        sb.append("Now you have " + taskList.size() + " tasks in the list.");
        assert sb.length() == 0 : "The stringbuilder should not be empty.";   
        return sb;
    }

    /**
     * Sets the status of a particular task in the array list of tasks to 
     * completed thereby signifying that the task has been completed.
     * @param index The index of the task in the array list to be set to completed to be marked as completed.
     * @param storage The Storage object handling the read and write from the local file storing the tasks.
     * @return A stringbuilder object containing the response to be given by Duke upon 
     *     marking a task as done successfully.
     * @throws IndexOutOfBoundsException When the index specified by the user does not exist.
     * @throws IOException When the file to be written to is not found or does not exist.
     */
    public StringBuilder markAsDone(int index, Storage storage) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            taskList.get(index - 1).markAsDone();
            storage.writeToFile(storage.getFilePath(), taskList);
            sb.append("Nice! I've marked this task as done: " + "\n");
            sb.append(taskList.get(index - 1).toString());
            assert sb.length() == 0 : "The stringbuilder should not be empty.";   
            return sb;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("☹ OOPS!!! The item specified does not exist.");
        }
    }

}