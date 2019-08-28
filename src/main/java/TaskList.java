import Task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public Task deleteTask(String position) {
        int index = Integer.parseInt(position) - 1;
        Task currTask = list.get(index);
        list.remove(index);
        return currTask;
    }

    public Task doTask(String position) {
        int index = Integer.parseInt(position) - 1;
        Task currTask = list.get(index);
        currTask.doTask();
        return currTask;
    }


}
