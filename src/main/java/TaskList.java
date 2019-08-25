import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a TaskList which stores Tasks. A TaskList with n items indexes tasks from 1 to n.
     * @param tasks An ArrayList of tasks which will be used to populate the task list.
     */
    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    int size() {
        return tasks.size();
    }

    boolean add(Task t) {
        return tasks.add(t);
    }

    Task get(int idx) {
        return tasks.get(idx - 1);
    }

    Task remove(int idx) {
        return tasks.remove(idx - 1);
    }

    @Override
    public String toString() {
        String result = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            result += "\n" + (i + 1) +  ". " + tasks.get(i);
        }
        return result;
    }
}
