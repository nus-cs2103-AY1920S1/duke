import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

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
