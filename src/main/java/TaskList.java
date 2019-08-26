import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return this.tasks.size();
    }

    public Task addTask(Task task) {
        this.tasks.add(task);
        return task;
    }

    public Task markDone(int index) {
        Task task = this.tasks.get(index - 1);
        task.setDone(true);
        return task;
    }

    public Task delete(int index) {
        Task task = this.tasks.get(index - 1);
        this.tasks.remove(index - 1);
        return task;
    }

    public List<String> getTaskNames() {
        List<String> taskNames = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            taskNames.add( (i + 1) + ". " + tasks.get(i));
        }
        return taskNames;
    }

    public List<String> getSimplifiedTaskRepresentations() {
        List<String> simplifiedTaskRepresentations = new ArrayList<>();
        for (Task task : tasks) {
            simplifiedTaskRepresentations.add(task.getSimplifiedRepresentation());
        }
        return simplifiedTaskRepresentations;
    }
}
