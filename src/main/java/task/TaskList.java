package task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> task;
    private int counter;

    public TaskList() {
        this.task = new ArrayList<>();
        this.counter = 0;
    }

    public TaskList(ArrayList<Task> task) {
        this.task = task;
        this.counter = task.size();
    }

    public TaskList(ArrayList<Task> task, int counter) {
        this.task = task;
        this.counter = counter;
    }

    public TaskList addTask(Task t) {
        task.add(t);
        Ui.printAddedTask(t, counter);
        counter++;
        return new TaskList(task, counter);
    }

    public TaskList deleteTask(int index) {
        Task deletedTask = task.remove(index);
        counter--;
        Ui.printDeleteTask(deletedTask, counter);
        return new TaskList(task, counter);
    }

    public TaskList doneTask(int index) {
        Task markAsDoneTask = task.get(index);
        markAsDoneTask.markAsDone();
        Ui.printDoneTask(markAsDoneTask);
        return new TaskList(task, counter);
    }

    public ArrayList<Task> getTasks() {
        return this.task;
    }

    public int getCounter() {
        return this.counter;
    }
}