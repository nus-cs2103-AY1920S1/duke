import java.util.ArrayList;

public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String LINE = "____________________________________________________________";

    /**
     * Greets the users, and asks users what they want Duke to do.
     * @return String Returns a String of Greetings.
     */
    public String showIntro() {
        String out = String.format("%s%n Hello! I am Duke%n " +
                "What can I do for you?%n", LOGO);
        return addLines(out);
    }

    /**
     * Lists out all the tasks the user has added, be it ToDo, Deadlines or Events,
     * in the order of input.
     * @param tasks An ArrayList which consists of the Task objects
     * @return String Returns a Strings of all Tasks.
     */
    public String showList(ArrayList<Task> tasks) {
        StringBuilder s = new StringBuilder();
        s.append(" Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            s.append(System.getProperty("line.separator"));
            s.append(" ").append(i).append(".");
            Task t = tasks.get(i - 1);
            s.append(t.toString());
        }
        s.append(System.getProperty("line.separator"));
        return addLines(s.toString());
    }

    /**
     * Bids the user GoodBye after the user is done using Duke.
     * @return String Returns a string of farewell words.
     */
    public String showFarewell() {
        String bye = String.format("GoodBye! Hope to see you again soon!%n");
        return addLines(bye);
    }

    /**
     * Adds a new Task to the list of tasks, and informs the user of the task added.
     * @param t The Task to be added, which can be a ToDo, Deadline, or Event.
     * @return String Returns a String of information notifying the user of the added task.
     */
    public static String newTask(Task t) {
        _task.add(t);
        String added = String.format("%s%n Got it! I've added this task:" +
                        "%n   %s%n Now you have %d task in the list.%n%s%n",
                line, t.toString(), _task.size(), line);
        return added;
    }

    /**
     * Marks a Task as done, and notifies the user of the task marked as done.
     * @param n The task number, in the order of input.
     * @return String Returns a string to inform user of the task marked as done.
     */
    public static String done(int n) {
        Task t = _task.get(n - 1);
        t.markAsDone();
        String done = String.format("%s%n Nice! I've marked this task as done:%n [%s] %s%n%s%n",
                line, t.getStatusIcon(), t.getDesc(), line);
        return done;
    }

    /**
     * Deletes a given Task from the list of all Tasks, then notifies the user of the
     * Task removed.
     * @param n The task number, in the order of input.
     * @return String Returns a string to inform user of the task removed from the list.
     */
    public static String delete(int n) {
        Task t = _task.get(n - 1);
        _task.remove(n - 1);
        String del = String.format("%s%n Noted. I've removed this task:%n   %s%n" +
                        "Now you have %d tasks in the list.%n%s%n",
                line, t.toString(), _task.size(), line);
        return del;
    }

    public static String addLines(String cmd) {
        String out = String.format("%s%n %s%n%s%n",
                LINE, cmd, LINE);
        return out;
    }
}
