import java.util.ArrayList;

/**
 * Represents a TaskList which manipulates the list of tasks.
 * @author Ang Kai Qi
 * @version 0.1.3
 */
public class TaskList {
    protected ArrayList<Task> tasklist;
    protected Storage storage;

    /**
     * Creates a TaskList object with the Storage object to be read from or written to.
     * @param storage To be read from or written to.
     */
    public TaskList(Storage storage) {
        this.tasklist = new ArrayList<Task>();
        this.storage = storage;
    }

    /**
     * Based on what processed task returns from the Parser, the TaskList would return and act accordingly.
     * If its adding or modifying of tasks, the UI and Storage would be activated. Bye statement would terminate
     * the TaskList object.
     *
     * @param s String from the Storage input file.
     * @return A task string for the UI and Storage to either echo or save.
     */
    public String addTask(String s) {
        String[] task = s.split(" "); // task array with many individual strings
        String statement = "";
        String[] modifiedTask = Parser.processTask(task); // modified task array {[command], [description], [date]}
        assert modifiedTask.length == 3 : "Parser failed to process command correctly";
        final String COMMAND = modifiedTask[0];
        final String DESCRIPTION = modifiedTask[1];
        final String DATE = modifiedTask[2];
        switch (COMMAND) {
        case "todo":
            ToDo todo = new ToDo(DESCRIPTION);
            tasklist.add(todo);
            statement = "Got it. I've added this task:\n  " + todo + "\nNow you have " + Task.getCurrTotal()
                            + " tasks in the list.";
            storage.save(tasklist);
            break;
        case "deadline":
            Deadline deadline = new Deadline(DESCRIPTION, DATE);
            tasklist.add(deadline);
            statement = "Got it. I've added this task:\n  " + deadline + "\nNow you have " + Task.getCurrTotal()
                            + " tasks in the list.";
            storage.save(tasklist);
            break;
        case "event":
            Event event = new Event(DESCRIPTION, DATE);
            tasklist.add(event);
            statement = "Got it. I've added this task:\n  " + event + "\nNow you have " + Task.getCurrTotal()
                            + " tasks in the list.";
            storage.save(tasklist);
            break;
        case "list":
            statement = checkList();
            break;
        case "done":
            statement = complete(Integer.parseInt(DESCRIPTION));
            storage.save(tasklist);
            break;
        case "delete":
            statement = delete(Integer.parseInt(DESCRIPTION));
            storage.save(tasklist);
        break;
        case "find":
            statement = findList(DESCRIPTION);
            break;
        case "bye":
            statement = "bye";
            break;
        default:
            statement = COMMAND;
            break;
        }
        return statement;
    }

    protected String checkList() {
        if (this.tasklist.isEmpty()) {
            return "\u2639 OOPS!!! I'm sorry, but there are no tasks in your list :-(";
        }
        String s = "Here are the tasks in your list:\n";
        for (int i = 0; i < this.tasklist.size(); i++) {
            Task t = this.tasklist.get(i);
            s += ((i+1) + "." + t + "\n");
        }
        return s.substring(0, s.length() - 1);
    }

    protected String findList(String keyword) {
        if (this.tasklist.isEmpty()) {
            return "\u2639 OOPS!!! I'm sorry, but there are no tasks in your list :-(";
        }
        String s = "Here are the matching tasks in your list:\n";
        int idx = 1;
        for (Task t : tasklist) {
            if (t.getDescription().contains(keyword)) {
                s += ("" + idx + t + "\n");
                idx++;
            }
        }
        return s.substring(0, s.length() - 1); // removing the extra "\n"
    }

    protected String complete(int i) {
        if (i >= tasklist.size()) {
            return "\u2639 OOPS!!! I'm sorry, but I have no such task in your list :-(";
        }
        Task t = this.tasklist.get(i - 1);
        t.setDone();
        String statement = "Nice! I've marked this task as done:\n  " + t;
        return statement;
    }

    protected String delete(int i) {
        if (i >= tasklist.size()) {
            return "\u2639 OOPS!!! I'm sorry, but I have no such task in your list :-(";
        }
        Task t = this.tasklist.remove(i - 1);
        Task.decCurrTotal();
        String statement = "Noted. I've removed this task:\n  " + t + "\nNow you have " + Task.getCurrTotal()
                            + " tasks in the list.";
        return statement;
    }
}
