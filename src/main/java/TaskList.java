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
        if (listItems.isEmpty()) {
            ps.println("\tYou currently have no tasks!");
        }
        for (int i = 1; i <= listItems.size(); i++) {
            ps.println("\t  " + i + ". " + listItems.get(i - 1));
        }
    }

    /**
     * Adds a task to the list based on the given command.
     * @param command The command given by the user to be processed.
     * @throws DukeException Exception thrown during the creation of
     *     the Task object if the command is invalid.
     */
    protected void addTask(String command) throws DukeException {
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
     * @throws DukeException Exception thrown if the ID input is invalid.
     */
    protected void markAsDone(int id) throws DukeException {
        if (id > listItems.size() || id <= 0) {
            throw new DukeException("The ID that you have entered is not a valid task ID");
        }
        Task task = listItems.get(id - 1);
        task.setDone();
        ps.println("\tNice! I've marked this task as done: \n"
                + "\t  " + task);
    }

    /**
     * Delete a task in the list based on its ID.
     * @param id the ID of the task that is to be deleted.
     * @throws DukeException Exception thrown if the ID input is invalid.
     */
    protected void delete(int id) throws DukeException {
        if (id > listItems.size() || id <= 0) {
            throw new DukeException("The ID that you have entered is not a valid task ID");
        }
        Task task = listItems.remove(id - 1);
        ps.println("\tNoted. I've removed this task:\n"
                + "\t  " + task + "\n"
                + "Now you have " + listItems.size() + " tasks in the list.");
    }
}
