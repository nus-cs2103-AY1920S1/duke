import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final String START_HORIZONTAL_LINE = "   ____________________________________________________________";
    public static final String END_HORIZONTAL_LINE = "   ____________________________________________________________\n";
    public static final String COMMAND_INDENTATION = "    ";
    public static final String COMPLETION_INDENTATION = "  ";
    public static final String BYE_MESSAGE = COMMAND_INDENTATION + "Bye. Hope to see you again soon!";
    public static final String GREETING_MESSAGE = COMMAND_INDENTATION + "Hello! I'm Duke\n" +
            COMMAND_INDENTATION + "What can I do for you?";
    public static final String ADDED_MESSAGE = COMMAND_INDENTATION + "added: ";
    public static final String LIST_MESSAGE = COMMAND_INDENTATION + "Here are the tasks in your list:";
    public static final String DONE_MESSAGE = COMMAND_INDENTATION + "Nice! I've marked this task as done:";

    private List<Task> taskList = new ArrayList<>();
    private Scanner myScanner;
    public static void main(String[] args) {
        Duke myDuke = new Duke();
        myDuke.run();
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        myScanner = new Scanner(System.in);
        displayGreetingMessage();
        while (myScanner.hasNextLine()) {
            String[] userInput = readCommand();
            executeCommand(userInput);
        }
    }

    /**
     * Formats the welcome message and print it.
     */
    public void displayGreetingMessage() {
        printLines(START_HORIZONTAL_LINE, GREETING_MESSAGE, END_HORIZONTAL_LINE);
    }

    /**
     * Prints message to the console.
     * @param messagesLines message to be printed to the console
     */
    public void printLines(String ... messagesLines) {
        for (String line : messagesLines) {
            System.out.println(line);
        }
    }

    /**
     * Reads user command entered by the user.
     * @return commands entered by the user
     */
    public String[] readCommand() {
        return myScanner.nextLine().trim().split(" ");
    }

    /**
     * Saves command as task name.
     * @param command the last command entered by the user
     */
    public void addCommandsEntered(String command) {
        Task myTask = new Task(command);
        taskList.add(myTask);
    }

    /**
     * Runs the commands entered by the user.
     * @param commands is the last command entered by the user
     */
    public void executeCommand(String ... commands) {
        if (commands[0].equals("bye") && commands.length == 1) {
            performsByeCommand();
        } else if (commands[0].equals("list") && commands.length == 1) {
            performsListCommand();
        } else if (commands[0].equals("done") && commands.length == 2) {
            performsDoneCommand(Integer.parseInt(commands[1]));
        } else {
            performsOthersCommand(String.join(" ", commands));
        }
    }

    /**
     * Performs others command
     * @param command is the last command entered by the user
     */
    public void performsOthersCommand(String command) {
        addCommandsEntered(command);
        printLines(START_HORIZONTAL_LINE, ADDED_MESSAGE + command, END_HORIZONTAL_LINE);
    }

    /**
     * Performs the command "done"
     * @param itemNum is the index of the task list
     */
    public void performsDoneCommand(int itemNum) {
        taskList.get(itemNum - 1).completeTask();
        printLines(START_HORIZONTAL_LINE, DONE_MESSAGE,
                COMMAND_INDENTATION + COMPLETION_INDENTATION + taskList.get(itemNum - 1).toString(), END_HORIZONTAL_LINE);
    }

    /**
     * Performs the command "list"
     */
    public void performsListCommand() {
        String formattedString = getTasks();
        printLines(START_HORIZONTAL_LINE, LIST_MESSAGE, COMMAND_INDENTATION + formattedString,
                END_HORIZONTAL_LINE);
    }

    /**
     * Performs the command "bye"
     */
    public void performsByeCommand() {
        printLines(START_HORIZONTAL_LINE, BYE_MESSAGE, END_HORIZONTAL_LINE);
        System.exit(0);
    }

    /**
     * Formats the list of tasks.
     * @return formatted list of tasks
     */
    public String getTasks() {
        StringBuilder myStringBuilder = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            if (i < taskList.size() - 1) {
                myStringBuilder.append(i + 1).append(".").append(taskList.get(i)).append("\n")
                        .append(COMMAND_INDENTATION);
            } else if (i == taskList.size() - 1) {
                myStringBuilder.append(i + 1).append(".").append(taskList.get(i));
            }
        }
        return myStringBuilder.toString();
    }
}
