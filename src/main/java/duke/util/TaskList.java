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

    public String output() {
        String taskString = "";
        for (int i = 1; i <= tasks.size(); i++) {
            taskString += (i + ". " + tasks.get(i - 1)) + System.lineSeparator();
        }
        return taskString;
    }

    public String markDone(int index) {
        tasks.get(index).done();
        String message = String.format("Nice! I've marked this task as done:%s%s",
                System.lineSeparator(), tasks.get(index));
        return message;
    }

    public String remove(int index) {
        String message = String.format("Noted. I've removed this task:%s%s%s",
                System.lineSeparator(), tasks.get(index), System.lineSeparator());
        tasks.remove(index);
        message += "You now have " + getSize() + (getSize() == 1 ? " task" : " tasks") + " in the list.";
        return message;
    }

    public String add(Task t) {
        tasks.add(t);
        String message = String.format("Got it. I've added this task:%s%s%s",
                System.lineSeparator(), t, System.lineSeparator());
        message += "You now have " + getSize() + (getSize() == 1 ? " task" : " tasks") + " in the list.";
        return message;
    }

}
