package duke.util;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public void output() {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i - 1));
        }
    }

    public void markDone(int index) {
        tasks.get(index).done();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + tasks.get(index));
    }

    public void remove(int index) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + tasks.get(index));
        tasks.remove(index);
        System.out.println("You now have " + getSize() + (getSize() == 1 ? "task" : " tasks") + " in the list.");
    }

    public void add(Task t) {
        tasks.add(t);
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + t);
        System.out.println("You now have " + getSize() + (getSize() == 1 ? "task" : " tasks") + " in the list.");
    }

}
