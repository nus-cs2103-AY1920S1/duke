import java.util.ArrayList;

public class Tasks {
    private ArrayList<Task> tasks;

    public Tasks() {
        this.tasks = new ArrayList<>();
    }

    public boolean addTask(Task task) {
        return tasks.add(task);
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < tasks.size(); ++i) {
            ret.append(i + 1).append(". ")
               .append(tasks.get(i).getContent())
               .append("\n");
        }
        return ret.toString();
    }
}
