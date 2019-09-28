package duke.task;

import java.util.ArrayList;

/**
 * A list of current tasks with various operations on the list.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs a new instance of TaskList with no given parameter.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs a new instance of TaskList and sets its list to be the given parameter.
     *
     * @param lst list to be set.
     */
    public TaskList(ArrayList<Task> lst) {
        this.list = lst;
    }

    /**
     * Informs the size of the stored list of tasks.
     *
     * @return size of the list of tasks.
     */
    public int size() {
        return list.size();
    }

    /**
     * Gives the task at specified index position.
     *
     * @param index index position of the task.
     * @return a Task object at the specified position.
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Adds a new task into the list of tasks.
     *
     * @param index index position where the task will be added.
     * @param t the task to be added.
     */
    public void add(int index, Task t) {
        list.add(index, t);
    }

    /**
     * Removes a task from the list of tasks.
     *
     * @param index index position where the task will be removed.
     */
    public void remove(int index) {
        list.remove(index);
    }

    /**
     * Provides the current list of tasks.
     *
     * @return an ArrayList of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return list;
    }

    /**
     * Informs all relevant tasks that contain the given keyword.
     *
     * @param keyWord information to appear in the descriptions of certain tasks.
     * @return an ArrayList of tasks that contain the given keyword.
     */
    public ArrayList<Task> returnAllMatchingTasks(String keyWord) {
        ArrayList<Task> lst = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            if (t.getDescription().toLowerCase().contains(keyWord.toLowerCase())) {
                lst.add(t);
            }
        }
        return lst;
    }

}
