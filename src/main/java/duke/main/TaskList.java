package duke.main;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasksList;

    public TaskList() {
        this.tasksList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }

    public void addItem(Task task) {
        tasksList.add(task);
    }

    public int size() {
        return tasksList.size();
    }

    public Task markNumberedTaskAsDone(int number) {
        Task task = getTaskAtIndex(number - 1);
        task.markAsDone();
        return task;
    }

    public ArrayList<Task> getTasksList() {
        return tasksList;
    }

    public Task deleteTaskAtNumber(int position) {
        return tasksList.remove(position - 1);
    }

    public Task getTaskAtIndex(int position) {
        return tasksList.get(position);
    }

}
