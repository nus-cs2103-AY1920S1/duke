import java.util.LinkedList;

public class TaskList {
    LinkedList<Task> tasks;

    /**
     * Constructor for case with no existing save file.
     */
    public TaskList() {
        this.tasks = new LinkedList<Task>();
    }

    /**
     * Constructor for case with existing save file.
     *
     * @param lst LinkedList storing existing tasks
     */
    public TaskList(LinkedList<Task> lst) {
        this.tasks = lst;
    }

    /**
     * Returns task at given id in linkedlist.
     *
     * @param id index in linkedlist
     * @return task
     */
    public Task getTask(int id) throws DukeException {
        try {
            return this.tasks.get(id - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! No such task exist !");
        }
    }

    /**
     * Gets all tasks.
     *
     * @return linkedlist containing all tasks
     */
    public LinkedList<Task> getAllTasks() {
        return this.tasks;
    }

    /**
     * Deletes task.
     *
     * @param i index in linkedlist
     * @throws DukeException if index out of bounds
     */
    public void deleteTask(int i) throws DukeException {
        try {
            this.tasks.remove(i - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! No such task exist!");
        }
    }

    /**
     * Marks task at index as complete.
     *
     * @param i index in linkedlist
     * @throws DukeException if index out of bounds
     */
    public void completeTask(int i) throws DukeException {
        try {
            this.tasks.get(i - 1).setDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! No such task exist!");
        }
    }

    /**
     * Adds task to linkedlist.
     *
     * @param t task to be added
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Returns total number of tasks.
     *
     * @return size of linkedlist
     */
    public int getListSize() {
        return this.tasks.size();
    }

    /**
     * Returns a list of tasks which contains the keyword.
     *
     * @return list of tasks
     */
    public LinkedList<Task> findTasks(String keywords) {
        LinkedList<Task> results = new LinkedList<>();
        for (Task t : this.tasks) {
            if (t.toString().contains(keywords)) {
                results.add(t);
            }
        }
        return results;
    }

    /**
     * Checks all tasks in the Tasklist to see if t is a duplicate.
     *
     * @return true if task already exist
     */
    public boolean isDuplicate(Task t) {
        for (Task task : tasks) {
            if (task.equals(t)) {
                return true;
            }
        }

        return false;
    }
}
