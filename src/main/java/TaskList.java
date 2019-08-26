import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task getTask(int taskNumber) {
        return this.tasks.get(taskNumber);
    }

    public void deleteTask(int taskNumber) {
        this.tasks.remove(taskNumber);
    }

    public void markAsDone(int taskNumber) {
        this.tasks.get(taskNumber).markAsDone();
    }

    public int taskListSize() {
        return this.tasks.size();
    }

    public TaskList findTasks(String keyWord) {
        TaskList tasksWithKeyWord = new TaskList();

        for (int i = 0; i < this.tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            String[] descriptionSplit = currentTask.getDescription().split(" ");
            List<String> descriptionWords = Arrays.asList(descriptionSplit);
            if (descriptionWords.contains(keyWord)) {
                tasksWithKeyWord.addTask(currentTask);
            }
        }

        return tasksWithKeyWord;
    }

}
