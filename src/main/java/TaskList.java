import javax.print.ServiceUI;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents an ArrayList of tasks.
 */
public class TaskList implements Serializable {
    public ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Prints all the tasks in the TaskList.
     */
    public String listTasks() {
        int size = this.tasks.size();
        StringBuilder listOfTask = new StringBuilder();
        for (int i = 0; i < size; i++) {
            listOfTask.append(i + 1 + ". " + this.tasks.get(i) + "\n" + "     ");
        }
        return (listOfTask.toString());

    }

    /**
     * Returns all the tasks in the TaskList in the form of an ArrayList.
     *
     * @return the tasks in the TaskList in an ArrayList.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param index task to be deleted.
     */
    public void deleteTask(int index) throws DukeException {
        try {
            this.tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! Please input a valid number.");
        }
    }

    /**
     * Returns the total number of tasks.
     *
     * @return the total number of tasks.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns a task at the given index.
     *
     * @param index index of task to be returned.
     * @return task at the given index.
     */
    public Task getTask(int index) throws DukeException{
        try {
            return this.tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! Please input a valid number.");
        }
    }

}
