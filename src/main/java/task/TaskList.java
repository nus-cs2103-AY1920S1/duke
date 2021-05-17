package task;

import java.util.ArrayList;

/**
 * Represents the list of tasks that the user has.
 */
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

    public int size() {
        return tasks.size();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Converts the list of tasks in this object into its String representation so as to be stored in a text file.
     *
     * @return String representation of this list of tasks.
     */
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
                break;
            case DEADLINE:
                result.append("D | ");
                result.append(task.isDone);
                result.append(" | ");
                result.append(task.getDescription());
                result.append(" | ");
                result.append(task.getTime());
                break;
            case EVENT:
                result.append("E | ");
                result.append(task.isDone);
                result.append(" | ");
                result.append(task.getDescription());
                result.append(" | ");
                result.append(task.getTime());
                break;
            default:
                break;
            }

            // add priority level
            result.append(" | ");
            result.append(task.getPriority() == null ? "null" : task.getPriority().toString());
            result.append("\n");
        }

        return result.toString();
    }
}
