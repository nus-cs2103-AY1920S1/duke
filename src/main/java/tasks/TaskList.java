package tasks;

import tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> commandList;

    public TaskList(ArrayList<Task> commandList) {
        this.commandList = commandList;
    }

    public ArrayList<Task> getCommandList() {
        return commandList;
    }

    public void deleteTask(int number) {
        this.getCommandList().remove(number - 1);
    }

    public void addTask(Task task) {
        this.commandList.add(task);
    }
}
