package tasks;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        list = taskList;
    }

    public int getSize() {
        return list.size();
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public void addTask(Task newTask) {
        list.add(newTask);
    }

    /**
     * delete a task from the list.
     *
     * @param number task number
     * @return return the deleted task
     */
    public Task deleteTask(int number) {
        Task deletedTask = list.get(number - 1);
        list.remove(deletedTask);
        return deletedTask;
    }

    public Task markDone(int number) {
        list.get(number - 1).changeStatus();
        return list.get(number - 1);
    }

    public Task getTask(int number) {
        return list.get(number - 1);
    }


}
