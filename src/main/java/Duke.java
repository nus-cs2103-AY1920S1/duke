import java.util.ArrayList;
import java.lang.Integer;

/**
 * Encapsulates attributes and behaviour of Duke, a personal assistant chatbot.
 *
 * Duke greets the user, stores tasks entered by the user, marks tasks as done as
 * requested by the user, and exits when the user types 'bye'. Duke can retrieve
 * stored tasks and their status in a user readable format.
 *
 * @author atharvjoshi
 * @contributors j-lum, damithc
 * @version CS2103 AY19/20 Sem 1 iP Week 2
 */
public class Duke {
    /** a unique logo for Duke */
    private final String logo;

    /** welcome message */
    private String welcomeMessage;

    /** exit message */
    private String exitMessage;

    /** flag to indicate if Duke is listening to commands from the user */
    private boolean isListening;

    /** a list of all tasks entered by the user */
    private ArrayList<Task> tasks;

    /**
     * Creates and initialises an instance of Duke. Duke's logo is assigned
     * only once.
     */
    public Duke() {
        this.logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        this.welcomeMessage = "Hello! I'm Duke\n"
                + "What can I do for you?\n";

        this.exitMessage = "Bye. Hope to see you again soon!\n";

        // when an instance of Duke is created, it starts listening to commands
        this.isListening = true;

        this.tasks = new ArrayList<Task>();
    }

    /**
     * Greets the user with the welcome message
     */
    public void greet() {
        System.out.print(welcomeMessage);
    }

    /**
     * Processes a command entered by the user.
     *
     * @param command a string containing the command(s) entered by the user
     */
    public void processCommand(String command) {
        if (command.equalsIgnoreCase("bye")) {
            // user wants to quit, so Duke stops listening to commands and exits
            this.isListening = false;
            this.exit();
        } else if (command.equalsIgnoreCase("list")) {
            // user wants to print all tasks entered
            this.printTasks();
        } else if (command.split(" ")[0].equalsIgnoreCase("done")) {
            String taskNumber = command.split(" ")[1];
            int taskIndex = Integer.parseInt(taskNumber) - 1;
            this.markTaskAsDone(this.tasks.get(taskIndex));
        } else {
            // add command to task list and inform user
            this.storeTask(command);
        }
    }

    /**
     * Exits the chatbot application with an exit message
     */
    private void exit() {
        System.out.print(exitMessage);
    }

    /**
     * Returns the unique logo of Duke
     *
     * @return a string representation of Duke's a logo
     */
    public String getLogo() {
        return this.logo;
    }

    /**
     * Returns the current listening state of Duke
     *
     * @return true if Duke is currently listening to commands, false otherwise
     */
    public boolean getIsListening() {
        return (this.isListening);
    }

    /**
     * Prints all tasks entered by the user in a readable format
     */
    private void printTasks() {
        // inform user if the list is empty
        if (this.tasks.isEmpty()) {
            System.out.println("No tasks in your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            int listSize = this.tasks.size(); // find number of commands entered
            for (int i = 0; i < listSize; i++) {
                // specified format: "1. task 1"
                Task currentTask = this.tasks.get(i);
                System.out.format("%d. [%s] %s\n", i + 1,
                        currentTask.getTaskStatusIcon(),
                        currentTask.getDescription());
            }
        }
    }

    /**
     * Stores commands entered by the user as tasks
     *
     * @param command the command entered by the user
     */
    private void storeTask(String command) {
        Task task = new Task(command);
        this.tasks.add(task);
        System.out.println("added: " + command);
    }

    /**
     * Marks tasks as done and prints a message for the user
     *
     * @param task the task to be marked as done
     */
    private void markTaskAsDone(Task task) {
        task.setTaskAsDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.format("   [%s] %s\n", task.getTaskStatusIcon(),
                task.getDescription());
    }
}