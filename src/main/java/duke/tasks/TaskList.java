package duke.tasks;

import java.util.ArrayList;

/**
 * list of tasks
 */
public class TaskList {
    public ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * add the task to the list
     * 
     * @param t task to be added
     */
    public void add(Task t) {
        list.add(t);
    }

    /**
     * delete the task from the list
     * 
     * @param i index of the task to be deleted
     * @return returns the task that was deleted
     */
    public Task remove(int i) {
        return list.remove(i-1);
    }

    /**
     * fetches the task from the list
     * 
     * @param i index of the task mentioned
     * @return the task with the index i
     */
    public Task get(int i) {
        return list.get(i-1);
    }
    /**
     * 
     * @param keyword the search term
     * @return a task list of all matching tasks
     */
    public ArrayList<TaskWithOrder> search(String keyword) {
        ArrayList<TaskWithOrder> res = new ArrayList<>();
        String queryStr = ".*" + keyword + ".*";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).content.matches(queryStr)) {
                res.add(new TaskWithOrder(i, list.get(i)));
            }
        }
        return res;
    }

    /**
     * prints the task list with numberings
     */
    public String toString() {
        String res = "";
        for (int i = 0; i < list.size(); i++) {
            res += (i+1) + "." + list.get(i) + "\n";
        }
        return res;
    }
}