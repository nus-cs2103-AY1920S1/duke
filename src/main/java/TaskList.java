package duke.task;

import java.util.ArrayList;

import duke.exception.InvalidArgument;

/**
 * Represents the TaskList object. Contains method to add, remove and mark a
 * task within the tasklist as done
 */
public class TaskList {
    private ArrayList<Task> tasklist;

    /**
     * Creates a tasklist object that contains all the tasks in an ArrayList.
     * 
     * @param list an ArrayList with all the tasks normally generated from the
     *             storage file
     */
    public TaskList(ArrayList<Task> list) {
        this.tasklist = list;
    }

    /**
     * Returns the list of all the tasks within the tasklist.
     * 
     * @return an ArrayList of all the tasks within the tasklist
     */
    public ArrayList<Task> getList() {
        return this.tasklist;
    }

    /**
     * Adds a task to the tasklist.
     * 
     * @param task the task to be added to the tasklist
     */
    public void addTask(Task task) {
        tasklist.add(task);
    }

    /**
     * Removes a task from the tasklist based on its index and return that task.
     * 
     * @param index an integer value of the index of the task to be removed
     * @return the task that was removed from the task list
     */
    public Task removeTask(int index) throws InvalidArgument {
        try {
            return tasklist.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgument();
        }
    }

    /**
     * Retrieves a task from the tasklist based on its index.
     * 
     * @param index an integer value of the index of the task to be retrieved
     * @return the task that was specified
     */
    public Task getTask(int index) throws InvalidArgument {
        try {
            return tasklist.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgument();
        }
    }

    /**
     * Returns the number of task within the task list.
     * 
     * @return an integer value of the number of task within the list
     */
    public int size() {
        return tasklist.size();
    }

    /**
     * Find tasks based on their description.
     * 
     * @param search the string to search for among the description
     */
    public ArrayList<FindPair> find(String search) {
        ArrayList<FindPair> result = new ArrayList<>();
        for (Task t : tasklist) {
            FindPair temp;
            if (t.toString().contains(search)) {
                temp = new FindPair(t, true);
            } else {
                temp = new FindPair(t, false);
            }
            result.add(temp);
        }
        return result;
    }
}