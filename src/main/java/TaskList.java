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
        return list.remove(i);
    }

    /**
     * fetches the task from the list
     * 
     * @param i index of the task mentioned
     * @return the task with the index i
     */
    public Task get(int i) {
        return list.get(i);
    }
    /**
     * 
     * @param keyword the search term
     * @return a task list of all matching tasks
     */
    public TaskList search(String keyword) {
        TaskList res = new TaskList();
        String queryStr = ".*" + keyword + ".*";
        for (Task t : list) {
            if (t.content.matches(queryStr)) {
                res.add(t);
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
            res += list.get(i).order + "." + list.get(i) + "\n";
        }
        return res;
    }
}