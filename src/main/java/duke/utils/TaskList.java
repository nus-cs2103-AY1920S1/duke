package duke.utils;

import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Event;
import duke.tasks.Deadline;
import duke.exceptions.DukeException;

import java.util.ArrayList;

/**Wrapper class around an ArrayList<Task> meant to simplify the addition,
 * deletion and completion of tasks.
 */
public class TaskList {
    ArrayList<Task> allTasks;

    /**Constructor
     * @param allTasks a collection of Tasks
     */
    public TaskList(ArrayList<Task> allTasks) {
        this.allTasks = allTasks;
    }

    /**Add a ToDo Task object to TaskList*/
    public Task addToDo(String taskDescription) {
        Task t = new ToDo(taskDescription);
        this.allTasks.add(t);
        return t;
    }

    /**Add a Deadline Task object to TaskList*/
    public Task addDeadline(String taskDescription, String deadline) throws DukeException {
        Task t = new Deadline(taskDescription, deadline);
        this.allTasks.add(t);
        return t;
    }

    /**Add an Event Task object to TaskList*/
    public Task addEvent(String taskDescription, String startTime, String endTime) throws DukeException {
        Task t = new Event(taskDescription, startTime, endTime);
        this.allTasks.add(t);
        return t;
    }

    /**Deletes a Task object from TaskList*/
    public Task deleteTask(int taskNum) throws DukeException {
        //Returns removed Task
        try {
            Task t = this.allTasks.get(taskNum - 1);
            this.allTasks.remove(taskNum - 1);

            return t;
        }
        catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task number is invalid!");
        }
    }

    /**Marks a Task object in TaskList as complete*/
    public Task completeTask(int taskNum) throws DukeException{
        try {
            Task t = this.allTasks.get(taskNum - 1); //Because storedTasks is zero-indexed
            t.markAsDone();
            return t;
        }
        catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task number is invalid!");
        }
    }

    /**Returns the size of the underlying ArrayList
     * @return an integer representing the number of Tasks in the TaskList
     */
    public int size() {
        return this.allTasks.size();
    }

    /**Returns the underlying ArrayList<Task>
     * @return underlying ArrayList<Task>
     */
    public ArrayList<Task> getArrayList() {
        return this.allTasks;
    }
}
