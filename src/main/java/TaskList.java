import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task markAsDone(int index) throws DukeException {
        index--;
        validateIndex(index);
        tasks.get(index).markAsDone();
        return tasks.get(index);
    }

    public Task deleteTask(int index) throws DukeException {
        index--;
        validateIndex(index);
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    public Task get(int index) throws DukeException {
        index--;
        validateIndex(index);
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public String printTasksForHardDisk() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tasks.size(); ++i) {
            builder.append(tasks.get(i).toStringForHardDisk());
            builder.append("\n");
        }
        return builder.toString();
    }

    private void validateIndex(int index) throws DukeException {
        if (!(0 <= index && index < tasks.size())) {
            throw new DukeException("Invalid task index.");
        }
    }
}
