import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listItems;
    private PrintStream ps = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    protected TaskList() {
        this.listItems = new ArrayList<>();
    }

    /**
     * Prints the list of items in the order as stored by the program.
     */
    protected void printList() {
        for (int i = 1; i <= listItems.size(); i++) {
            ps.println("\t  " + i + ". " + listItems.get(i - 1));
        }
    }

    /**
     * Adds a task to the list based on the given command.
     * @param command The command given by the user to be processed.
     */
    protected void add(String command) {
        Task newTask = Task.create(command);
        listItems.add(newTask);
        String taskSingular = listItems.size() == 1 ? "task" : "tasks";
        ps.println("\t" + "Got it. I've added this task: \n"
                + "\t  " + newTask + "\n"
                + "\t" + "Now you have " + listItems.size() + " " + taskSingular + " in the list.");
    }

    /**
     * Mark a task in the list as done based on its ID.
     * @param id the ID of the task that is done.
     */
    protected void markAsDone(int id) {
        Task task = listItems.get(id - 1);
        task.setDone();
        ps.println("\tNice! I've marked this task as done: \n"
                + "\t  " + task);
    }
}
