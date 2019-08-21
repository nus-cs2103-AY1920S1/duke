import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class TaskList {

    protected List<Task> taskList;

    protected TaskList() {
        this.taskList = new ArrayList<>();
    }

    protected String getTaskList() {
        StringBuilder tasks = new StringBuilder("");
        int count = 1;
        for (Task task : this.taskList) {
            tasks.append(count++ + ". " + task.getStatus() + "\n");
        }
        return tasks.toString();
    }

    // Index starts from 1
    protected void markAsDoneTaskAt(int index) {
        this.taskList.get(index - 1).markAsDone();
    }

    // Add task to the back of list and return added Task object
    protected Task addTask(Task task) {
        this.taskList.add(task);
        return task;
    }

    // Index starts from 1
    protected Task getTaskAt(int index) {
        return this.taskList.get(index - 1);
    }

    protected int getSize() {
        return this.taskList.size();
    }

}