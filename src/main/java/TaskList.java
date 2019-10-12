import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;

    /**
     * Represents the list of tasks which the chatbot user has.
     * @param list refers to the list of tasks
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Returns the number of items in the task list.
     * @return int listSize
     */
    public int getListSize() {
        return list.size();
    }

    /**
     * Returns the task at a particular place in the sequence.
     * @param i refers to the index of the task which needs to be fetched.
     * @return Task at the specified index
     */
    public Task getItemAtIndex(int i) {
        return list.get(i);
    }

    /**
     * Returns the last item in the list of tasks.
     * @return last task item
     */
    public Task getLastItem() {
        return list.get(list.size() - 1);
    }

    /**
     * Returns the tasklist.
     * @return Arraylist of Task
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Adds a new task to the list of tasks.
     * @param tsk refers to the new task which needs to be added.
     */
    public void addItemToList(Task tsk) {
        list.add(tsk);
    }

    /**
     * Marks the specified task as done.
     * @param i refers to the index of the task in the list
     *          which needs to be marked as done
     */
    public void markAsDone(int i) {
        list.get(i).markAsDone();
    }

    /**
     * Removed the specified task from the list.
     * @param i refers to the index of the task which needs to
     *          be removed from the list
     * @return the task removed
     */
    public Task removeFromList(int i) {
        return list.remove(i);
    }

    /**
     * Checks whether a task already exists in the list.
     * @param tsk refers to the task which is being checked
     * @return boolean whether a task exists in the list
     */
    public boolean contains(Task tsk) {
        for (int i = 0; i < list.size(); i++) {
            if (tsk.toString().equals(list.get(i).toString())) {
                return true;
            }
        }
        return false;
    }
}
