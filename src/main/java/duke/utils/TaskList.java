package duke.utils;

import duke.tasks.Task;

import java.util.LinkedList;

/**
 * Represents the list that contains every task.
 */
public class TaskList {
    private LinkedList<Task> li;

    /**
     * Creates an object that stores all the tasks information.
     * @param li list of that tasks
     */
    public TaskList(LinkedList<Task> li) {
        this.li = li;
    }

    /**
     * Prints the tasks that are in the list.
     */
    public String printList() {
        if (!li.isEmpty()) {
            String msg = "Here are the tasks in your list:\n";
            for (int i = 0; i < li.size(); i++) {
                int j = i + 1;
                msg = msg + "" + j + ". " + li.get(i).toString() +"\n";
            }
            return msg;
        } else {
            return "The task list is empty";
        }
    }

    /**
     * Gives the size of the TaskList which in
     * this case is the number of tasks in the TaskList.
     *
     * @return size of the TaskList
     */
    public int size() {
        return li.size();
    }

    /**
     * Get a certain task in the TaskList.
     *
     * @param i the task number in the TaskList
     * @return Task that is at index i
     */
    public Task getTask(int i) {
        return li.get(i);
    }

    /**
     * Removes the task from the TaskList.
     *
     * @param i index which user wants to remove
     */
    public void remove(int i) {
        li.remove(i);
    }

    /**
     * Adds tasks to the TaskList.
     *
     * @param t Task that is added to the TaskList
     */
    public void add(Task t) {
        li.add(t);
    }

    /**
     * Gets the list in TaskList.
     *
     * @return the list of all task that are in the TaskList
     */
    public LinkedList<Task> getList() {
        return li;
    }


}