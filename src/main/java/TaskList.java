import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public String convertTasksToString() {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }

        StringBuilder result = new StringBuilder("");

        for (Task task : tasks) {
            TaskType type = task.getType();
            switch (type) {
                case TODO:
                    result.append("T | ");
                    result.append(task.isDone);
                    result.append(" | ");
                    result.append(task.getDescription());
                    result.append("\n");
                    break;
                case DEADLINE:
                    result.append("D | ");
                    result.append(task.isDone);
                    result.append(" | ");
                    result.append(task.getDescription());
                    result.append(" | ");
                    result.append(task.getTime());
                    result.append("\n");
                    break;
                case EVENT:
                    result.append("E | ");
                    result.append(task.isDone);
                    result.append(" | ");
                    result.append(task.getDescription());
                    result.append(" | ");
                    result.append(task.getTime());
                    result.append("\n");
                    break;
            }
        }

        return result.toString();
    }
}
