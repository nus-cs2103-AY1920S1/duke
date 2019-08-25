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

    /**
     * An enumeration of all commands handled by Duke, which currently include:
     * BYE, DEADLINE, DELETE, DONE, EVENT, LIST, TODO.
     */
    private enum Command {
        BYE {
            public void run(Duke duke) {
                duke.exit();
            }
        }, DEADLINE {
            public void run(Duke duke) {
                duke.storeTask(this.name());
            }
        }, DELETE {
            public void run(Duke duke) {
                duke.deleteTask(this.name());
            }
        }, DONE {
            public void run(Duke duke) {
                duke.markTaskAsDone(this.name());
            }
        }, EVENT {
            public void run(Duke duke) {
                duke.storeTask(this.name());
            }
        }, LIST {
            public void run(Duke duke) {
                duke.printTasks();
            }
        }, TODO {
            public void run(Duke duke) {
                duke.storeTask(this.name());
            }
        };

        /**
         * Abstract method to run command, specified by each command
         */
        public abstract void run(Duke duke);
    }

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

    /** current command being processed by Duke */
    private String currentCommand;

    /**
     * Creates and initialises an instance of Duke.
     * <p>
     * Duke is initialised with a unique logo, a welcome and exit message,
     * active status (listening or not listening), and an empty list of tasks
     * to be added to by the user.
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
     * <p>
     * This method runs through a list of accepted commands and handles
     * them accordingly. It prints an error message if it encounters an
     * unrecognised command.
     *
     * @param inputString a string containing the command(s) entered by the user
     */
    public void processCommand(String inputString) {
        // set current command
        this.currentCommand = inputString;

        // extract the imperative specified by the user to identify which
        // command is to be executed by Duke
        String imperative = inputString.split(" ")[0].toUpperCase();

        // try to create the command enum based on the imperative and run it
        // if the imperative entered is unrecognised, inform the user
        try {
            Command command = Command.valueOf(imperative);
            command.run(this);
        } catch (IllegalArgumentException exceptionOne) {
            System.out.println("You entered a command I do not understand :-(");
            System.out.println("Let's speak the same language! Type 'help' to "
                    + "see the list of commands I understand :-)");
        } catch (NullPointerException exceptionTwo) {
            // the user has entered an empty line, wait for next command
            return;
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
            // loop through each task in list and print it
            int listSize = this.tasks.size();
            for (int i = 0; i < listSize; i++) {
                // specified format e.g. "1. task 1"
                Task currentTask = this.tasks.get(i);
                System.out.format("%d. %s\n", i + 1, currentTask);
            }
        }
    }

    /**
     * Creates a new task based on given task type and attributes.
     *
     * @param taskType the type of task to be created - deadline, event, todo
     * @param taskAttributes the description and date/time of the task
     * @return the new task if one can be created, or null otherwise
     * @throws IndexOutOfBoundsException if incorrect input format encountered
     */
    private Task createNewTask(String taskType, String taskAttributes)
            throws IndexOutOfBoundsException {
        if (taskType.equals("TODO")) {
            return new Todo(taskAttributes);
        } else {
            try {
                // try to obtain the description and date/time information of
                // the deadline / event. Inform user if the input is in an
                // incorrect format.
                String[] splitTaskAttributes = taskAttributes.split("\\/");
                String taskDescription = splitTaskAttributes[0];
                String taskDateTime = splitTaskAttributes[1].split(" ", 2)[1];

                if (taskType.equals("DEADLINE")) {
                    return new Deadline(taskDescription, taskDateTime);
                } else if (taskType.equals("EVENT")) {
                    return new Event(taskDescription, taskDateTime);
                }
            } catch (IndexOutOfBoundsException exception) {
                System.out.println("Looks like your format is incorrect. "
                        + "Please follow <event type> <description> / <day/date/time>");
            }
        }
        return null;
    }

    /**
     * Stores tasks such as deadlines, events, and todos in the chatbot's
     * internal list of tasks.
     *
     * @param taskType the type of task to store
     * @throws IndexOutOfBoundsException if the user enters a task with an
     *      empty description.
     */
    private void storeTask(String taskType) throws IndexOutOfBoundsException {
        try {
            // try to get the task attributes from the current command
            // if the task attributes are empty, inform the user
            String taskAttributes = this.currentCommand.split(" ", 2)[1];

            // try to create a new task based on obtained attributes
            // add this new task to the list and inform the user
            Task newTask = this.createNewTask(taskType, taskAttributes);
            if (newTask != null) {
                this.tasks.add(newTask);
                System.out.println("Got it. I've added this task: \n"
                        + "  " + newTask);
                System.out.format("Now you have %d tasks in the list.\n",
                        this.tasks.size());
            }
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Please enter a description of the " +
                    taskType + " you want to get done :-)");
        }
    }

    /**
     * Marks the task specified by the user as 'done' and informs the user.
     *
     * @param commandType the command type i.e. "DONE".
     * @throws NumberFormatException if the user does not enter an integer
     *      after the command type 'done'.
     * @throws IndexOutOfBoundsException if the user provides an index
     *      bigger than the size of the list.
     */
    private void markTaskAsDone(String commandType)
            throws NumberFormatException, IndexOutOfBoundsException {
        if (this.tasks.isEmpty()) {
            // if the user is trying this command on an empty task list
            System.out.println("No tasks in your list to complete!");
        } else if (this.currentCommand.equalsIgnoreCase(commandType)) {
            // the user has not specified the index of the task to be marked
            System.out.println("Please specify the index of the task "
                    + "you wish to mark as completed!");
        } else {
            try {
                // try to get index of the task to be marked from the input
                String taskNumber = this.currentCommand.split(" ")[1];
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
            } catch (NumberFormatException exceptionOne) {
                // if the user does not specify an integer in the command
                System.out.println("Please input an integer which is the " +
                        "index of the task you wish to mark as done.");
            } catch (IndexOutOfBoundsException exceptionTwo) {
                // if the user specifies an index that is bigger than list size
                System.out.println("You've specified a 0 index or an index " +
                        "that is bigger than the size of your list!");
            }
        }
    }

    /**
     * Deletes a specified task from the list and informs the user.
     *
     * @param commandType the command type i.e. "DELETE".
     * @throws NumberFormatException if the user does not enter an integer
     *      after the command type 'delete'.
     * @throws IndexOutOfBoundsException if the user provides an index bigger
     *      than the size of the list.
     */
    private void deleteTask(String commandType)
            throws NumberFormatException, IndexOutOfBoundsException {
        if (this.tasks.isEmpty()) {
            // if the user is trying this command on an empty task list
            System.out.println("No tasks in your list to delete!");
        } else if (this.currentCommand.equalsIgnoreCase(commandType)) {
            // the user has not specified the index of the task to be marked
            System.out.println("Please specify the index of the task "
                    + "you wish to delete!");
        } else {
            try {
                // obtain the index of the task to be deleted
                String taskNumber = this.currentCommand.split(" ")[1];
                int taskIndex = Integer.parseInt(taskNumber) - 1;

                // retrieve task to be removed, remove it, and inform the user
                Task taskToRemove = this.tasks.get(taskIndex);
                this.tasks.remove(taskIndex);
                System.out.println("Noted. I've removed this task:");
                System.out.println("   " + taskToRemove);
                System.out.format("Now you have %d tasks in the list\n",
                        this.tasks.size());
            } catch (NumberFormatException exceptionOne) {
                // if the user does not specify an integer in the command
                System.out.println("Please input an integer which is the " +
                        "index of the task you wish to mark as done.");
            } catch (IndexOutOfBoundsException exceptionTwo) {
                // if the user specifies an index that is bigger than list size
                System.out.println("You've specified a 0 index or index "
                        + "that is bigger than the size of your list!");
            }
        }
    }
}