import java.util.ArrayList;

/**
 * Represents a task to be completed.
 * Contains the list of tasks in an ArrayList
 */
public class Task {
    protected String description;
    protected boolean isDone;
    public static ArrayList<Task> tasks = new ArrayList<>();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Adds a new task to the task list
     *
     * @param command  Description of task to be added
     */
    public static void addTask(String command) {
        tasks.add(new Task(command));
    }

    /**
     * Marks a task as done
     *
     * @param command  Command that includes the index of task to be marked
     */
    public static void markAsDone(String command) {
        String commandStub = command.substring(5, command.length());
        int completedTaskNumber = Integer.parseInt(commandStub);
        tasks.get(completedTaskNumber - 1).isDone = true;
        System.out.println("    Nice! I've marked this task as done: ");
        System.out.println("      " + tasks.get(completedTaskNumber - 1));
    }

    /**
     * Prints the entire task list with index
     *
     */
    public static void printTaskList() {
        System.out.println("    Here are the tasks in your list:\n");
        int index = 1;
        for (Task x : tasks) {
            System.out.println("    " + index + ". " + x);
            index++;
        }
    }

    /**
     * Determines if task is done
     *
     * @return tick symbol if done, X symbol if not done
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
