import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> tasks;
    protected Storage storage;

    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        this.storage = storage;
    }

    private void update() {
        try {
            storage.update(tasks);

        } catch (Exception e) {
            return;
        }
    }

    public String view() {
        String str = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            str += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return str.substring(0, str.length()-1);
    }

    public String add(Task t) {
        tasks.add(t);
        update();
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                tasks.get(tasks.size() - 1), tasks.size());
    }

    public String delete(int i) {
        String str = tasks.get(i-1).toString();
        tasks.remove(i-1);
        update();
        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list",
                str, tasks.size());
    }

    public String markDone(int i) {
        tasks.get(i-1).markAsDone();
        update();
        return "Nice! I've marked this task as done:\n  " + tasks.get(i-1);
    }
}
