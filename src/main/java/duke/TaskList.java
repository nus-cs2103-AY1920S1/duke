package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        list = new ArrayList<>();
        for (Task task : tasks) {
            list.add(task);
        }
    }

    public List<Task> getList() {
        return list;
    }

    public void addToList(Task task) {
        list.add(task);
    }

    public int getListSize() {
        return list.size();
    }

    public Task markAsDone(int num) {
        Task task = list.get(num - 1);
        task.setDone();
        return task;
    }

    public Task delete(int num) {
        Task deletedTask = list.get(num - 1);
        list.remove(num - 1);
        return deletedTask;
    }

    public void printList() {
        int i = 1;
        for (Task task : list) {
            System.out.println("    " + i + ". " + task);
            i++;
        }
    }
}
