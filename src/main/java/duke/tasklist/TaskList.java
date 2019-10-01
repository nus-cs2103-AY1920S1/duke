package duke.tasklist;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    public Task deleteTask(int index) throws IndexOutOfBoundsException{
        return taskList.remove(index);
    }

    public Task completeTask(int completedTaskIndex) {
        Task completedTask = taskList.get(completedTaskIndex);
        completedTask.taskComplete();
        return completedTask;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getListSize() {
        return taskList.size();
    }
}