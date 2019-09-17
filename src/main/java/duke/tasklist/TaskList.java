package duke.tasklist;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

/**
 * Class that represents the list of tasks.
 */
public class TaskList {

    /**
     * The array list of list of the tasks.
     */
    private ArrayList<Task> listOfTasks;

    /**
     * Constructor that takes in the storage object containing the list of tasks.
     * @param storage The storage containing the list in the hard drive.
     * @throws Exception Used to handle exception that occurs when constructing the class.
     */
    public TaskList(Storage storage) throws Exception {
        listOfTasks = storage.getList();
    }

    /**
     * Constructor that takes in nothing for when there is no file yet in the hard drive.
     */
    public TaskList() {
        listOfTasks = new ArrayList<Task>();
    }

    /**
     * Used to check if the list of tasks is empty.
     * @return True if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return listOfTasks.isEmpty();
    }

    /**
     * Used to indicate the size of the list of tasks.
     * @return The size of the list.
     */
    public int size() {
        return listOfTasks.size();
    }

    /**
     * Used to return the list of tasks.
     * @return ArrayList of the list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.listOfTasks;
    }

    /**
     * Used to obtain the task using a given index.
     * @param index The index of the task.
     * @return The task corresponding to the index given.
     */
    public Task get(int index) {
        return listOfTasks.get(index);
    }

    /**
     * Used to delete a task from the list of tasks.
     * @param task The task that the user wants to delete.
     */
    public void removeTask(Task task) {
        listOfTasks.remove(task);
    }

    /**
     * Used to add a general task.
     * @param task The task that is to be added.
     */
    public void addTask(Task task) {
        listOfTasks.add(task);
    }

    /**
     * Used to add a new Todo task into the list.
     * @param task A new todo task.
     */
    public void addTodo(Todo task) {
        listOfTasks.add(task);
    }

    /**
     * Used to add a new Event task into the list.
     * @param task A new Event task.
     */
    public void addEvent(Event task) {
        listOfTasks.add(task);
    }

    /**
     * Used to add a new Deadline task into the list.
     * @param task A new Deadline task.
     */
    public void addDeadline(Deadline task) {
        listOfTasks.add(task);
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 1; i <= listOfTasks.size(); i++) {
            if (i != listOfTasks.size()) {
                output += i + ". " + listOfTasks.get(i - 1);
                output += "\n";
            } else {
                output += i + ". " + listOfTasks.get(i - 1);
            }
        }
        return output;
    }
}
