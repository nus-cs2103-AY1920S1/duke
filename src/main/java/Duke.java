import java.util.ArrayList;
import java.lang.Integer;

/**
 * Encapsulates attributes and behaviour of Duke, a personal assistant chatbot.
 *
 * Duke manages a user's list of tasks. It can store, add to, remove from, or
 * modify the contents of the list. User input must come either in the form of
 * commands 'list', 'bye' 'done <task index>', 'delete <task index>' or storage
 * requests beginning with the type of task to be stored. Duke supports three
 * types of tasks - todos, deadlines, and events. Deadlines and events need to
 * be supplied with additional date or time information. The input format for
 * todos is 'todo <task description>' and that for deadlines and events is
 * '<task type> <task description> / <date/time>'. Deviating from this input
 * format results in Duke supplying error messages to the user.
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
     * Creates and initialises an instance of Duke.
     *
     * Duke is initialised with a unique logo, a welcome and exit message,
     * active status (listening or not listening), and an empty list of tasks
     * to be added to by the user.
     *
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
        // initialise ArrayList to store Tasks
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Greets the user with the welcome message.
     */
    public void greet() {
        System.out.print(welcomeMessage);
    }

    /**
     * Processes a command entered by the user.
     *
     * This method runs through a list of accepted commands and handles
     * them accordingly. It prints an error message if it encounters an
     * unrecognised command.
     *
     * @param command a string containing the command(s) entered by the user
     */
    public void processCommand(String command) {
        String imperative = command.split(" ")[0].toUpperCase();

        switch (imperative) {
            case "BYE":
                // user wants to quit, so Duke stops listening to commands and exits
                this.exit();
                break;
            case "LIST":
                // user wants to print all tasks entered
                this.printTasks();
                break;
            case "DONE":
                // user wants to mark a task at the specified index as done
                this.markTaskAsDone(command);
                break;
            case "DELETE":
                // user wants to delete a task at the specified index
                this.deleteTask(command);
                break;
            case "TODO":
                // Fallthrough
            case "DEADLINE":
                // Fallthrough
            case "EVENT":
                // valid task storing command found
                this.storeTask(command);
                break;
            default:
                System.out.println("OOPS!!! I'm sorry, but I don't know"
                        + " what that means :-(");
                break;
        }
    }

    /**
     * Exits the chatbot application with an exit message.
     */
    private void exit() {
        this.isListening = false;
        System.out.print(exitMessage);
    }

    /**
     * Returns the unique logo of Duke.
     *
     * @return a string representation of Duke's a logo
     */
    public String getLogo() {
        return this.logo;
    }

    /**
     * Returns the current listening state of Duke.
     *
     * @return true if Duke is currently listening to commands, false otherwise.
     */
    public boolean getIsListening() {
        return (this.isListening);
    }

    /**
     * Prints all tasks entered by the user in a readable format.
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
                System.out.format("%d. %s\n", i + 1, currentTask);
            }
        }
    }

    /**
     * Stores commands entered by the user based on the type of tasks specified.
     *
     * Throws an exception if the user enters a task with an empty description
     * or incorrect format.
     *
     * @param command the command entered by the user.
     */
    private void storeTask(String command) throws ArrayIndexOutOfBoundsException {
        try {
            // segment the input into two parts - imperative and task attribute
            String[] segmentedCommand = command.split(" ", 2);

            Task task = null; // initialise task instance

            // create the various kind of tasks based on the specified command
            switch (segmentedCommand[0].toUpperCase()) {
                case "TODO":
                    task = new Todo(segmentedCommand[1]);
                    break;
                case "DEADLINE":
                    // obtain task description and date/time attribute from segmented command
                    // only valid for deadline and event task types
                    String deadlineDescription = segmentedCommand[1].split("\\/")[0];
                    String dueDate = segmentedCommand[1].split("\\/")[1].substring(2);
                    task = new Deadline(deadlineDescription, dueDate);
                    break;
                case "EVENT":
                    // obtain task description and date/time attribute from segmented command
                    // only valid for deadline and event task types
                    String eventDescription = segmentedCommand[1].split("\\/")[0];
                    String dateTime = segmentedCommand[1].split("\\/")[1].substring(2);
                    task = new Event(eventDescription, dateTime);
                    break;
                default:
                    System.out.println("OOPS!!! I'm sorry, but I don't know"
                            + " what that means :-(");
                    break;
            }

            // store tasks in list and inform the user
            this.tasks.add(task);
            System.out.println("Got it. I've added this task: \n"
                    + "  " + task);
            System.out.format("Now you have %d tasks in the list.\n",
                    this.tasks.size());
        } catch (ArrayIndexOutOfBoundsException exception) {
            // if the user has simply typed the imperative without
            // specifying any task attributes, or entered an incorrect
            // input format, let the user know.
            if (command.equalsIgnoreCase("todo")
                    || command.equalsIgnoreCase("deadline")
                    || command.equalsIgnoreCase("event")) {
                System.out.println("OOPS!!! The description of a " + command
                        + " cannot be empty.");
            } else {
                System.out.println("OOPS!!! Incorrect Format. Please follow - "
                        + "<event type> <description> / <day/date/time>");
            }
        }
    }

    /**
     * Marks a specified task as done and prints a message for the user.
     *
     * @param command the command entered by the user.
     * @throws IndexOutOfBoundsException if the user tries to wokr on an empty
     * list, does not specify index of list, or provides an index bigger than
     * the size of the list.
     */
    private void markTaskAsDone(String command) throws IndexOutOfBoundsException {
        try {
            // obtain the index of the task to be marked as done from the input
            String taskNumber = command.split(" ")[1];
            int taskIndex = Integer.parseInt(taskNumber) - 1;

            // retrieve task from list, mark as done, and inform the user
            Task currentTask = this.tasks.get(taskIndex);
            if (!currentTask.getIsDone()) {
                currentTask.setTaskAsDone(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("   " + currentTask);
            } else {
                System.out.println("This task has already been done!");
            }
        } catch (IndexOutOfBoundsException exception) {
            if (this.tasks.isEmpty()) {
                // if the user is trying this command on an empty task list
                System.out.println("No tasks in your list to complete!");
            } else if (command.equalsIgnoreCase("done")) {
                // if the user did not specify the index of the task to be marked
                System.out.println("Please specify the index of the task "
                        + "you wish to mark as completed!");
            } else {
                // if the user specified an index larger than the size of the
                // task list
                System.out.println("OOPS! You've specified an index that is "
                        + "bigger than the size of your list!");
            }
        }
    }

    /**
     * Deletes a specified task from the list and informs the user.
     *
     * @param command the command entered  by the user.
     * @throws IndexOutOfBoundsException if the user tries to wokr on an empty
     *      * list, does not specify index of list, or provides an index bigger than
     *      * the size of the list.
     */
    private void deleteTask(String command) throws IndexOutOfBoundsException {
        try {
            // obtain the index of the task to be deleted
            String taskNumber = command.split(" ")[1];
            int taskIndex = Integer.parseInt(taskNumber) - 1;

            // retrieve task to be removed, remove it, and inform the user
            Task taskToRemove = this.tasks.get(taskIndex);
            this.tasks.remove(taskIndex);
            System.out.println("Noted. I've removed this task:");
            System.out.println("   " + taskToRemove);
            System.out.format("Now you have %d tasks in the list\n", this.tasks.size());
        } catch (IndexOutOfBoundsException exception) {
            // if the user tries to delete from an empty list
            if (this.tasks.isEmpty()) {
                System.out.println("No tasks in your list to delete!");
            } else if (command.equalsIgnoreCase("delete")) {
                // if the user does not specify the index of the item to be deleted
                System.out.println("Please specify the index of the task "
                        + "you wish to delete!");
            } else {
                // if the user specifies an index that is bigger than the size
                // of the task list
                System.out.println("OOPS! You've specified an index that is "
                        + "bigger than the size of your list!");
            }
        }
    }
}