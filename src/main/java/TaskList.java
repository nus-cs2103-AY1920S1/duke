import Exception.DukeException;
import Tasks.Task;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> list = new ArrayList<Task>();

    public TaskList() {}

    public TaskList(ArrayList<Task> list) throws DukeException {
        this.list = list;
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public Task getTask(int n) {
        return list.get(n);
    }

    public int getSize() {
        return list.size();
    }

    public void addTask(Task t) {
        list.add(t);
    }

    public Task markTaskAsDone(int n) {
        Task task = list.get(n);
        task.markAsDone();
        return task;
    }

    public Task removeTask(int n) {
        return list.remove(n);
    }

    public void printList() {
        int length = list.size();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= length; i++) {
            Task task = list.get(i - 1);
            System.out.println(i + ". " + task);
        }
    }
}