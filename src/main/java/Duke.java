import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final String START_HORIZONTAL_LINE = "   ____________________________________________________________";
    public static final String END_HORIZONTAL_LINE = "   ____________________________________________________________\n";
    public static final String COMMAND_INDENTATION = "    ";
    public static final String COMPLETION_INDENTATION = "  ";

    private List<Task> taskList;
    private Scanner myScanner;

    public static final String BYE_MESSAGE = COMMAND_INDENTATION + "Bye. Hope to see you again soon!";
    public static final String GREETING_MESSAGE = COMMAND_INDENTATION + "Hello! I'm Duke\n" +
            COMMAND_INDENTATION + "What can I do for you?";
    public static final String ADDED_MESSAGE = COMMAND_INDENTATION + "added: ";
    public static final String LIST_MESSAGE = COMMAND_INDENTATION + "Here are the tasks in your list:";
    public static final String DONE_MESSAGE = COMMAND_INDENTATION + "Nice! I've marked this task as done:";
    public static final String ADDED_TASK_MESSAGE = COMMAND_INDENTATION + "Got it. I've added this task:";

    public final String LIST_SIZE_FORMAT = COMMAND_INDENTATION + "Now you have %d tasks in the list.";


    public static void main(String[] args) {
        Duke myDuke = new Duke();
        myDuke.run();
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        taskList = new ArrayList<>();
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
    public void executeCommand(String[] commands) {
        if (commands[0].equals("bye") && commands.length == 1) {
            performsByeCommand();
        } else if (commands[0].equals("list") && commands.length == 1) {
            performsListCommand();
        } else if (commands[0].equals("done") && commands.length == 2) {
            performsDoneCommand(Integer.parseInt(commands[1]));
        } else if (commands[0].equals("todo") && commands.length > 1) {
            performsTodoCommand(commands);
        } else if (commands[0].equals("deadline")) {
            performsDeadlineCommand(commands);
        } else if (commands[0].equals("event")) {
            performsEventsCommand(commands);
        } else {
            performsOthersCommand(String.join(" ", commands));
        }
    }

    /**
     * Retrieves two args from an arrays
     * @param delimiter is the string that determine the 2 arguments
     * @param commands is the latest command inputted by the user
     * @return
     */
    public String[] getTwoCommandArgs(String delimiter, String[] commands) {
        String[] args = new String[2];
        List<String> commandList = Arrays.asList(commands);
        int indexOfSeparator = commandList.indexOf(delimiter);
        if (indexOfSeparator != 1) {
            args[0] = concatStrings(commandList.subList(1, indexOfSeparator).toArray(new String[indexOfSeparator - 1]));
            args[1] = concatStrings(commandList.subList(indexOfSeparator + 1, commandList.size()).toArray(new String[commandList.size() - (indexOfSeparator + 1)]));
        }
        return args;
    }


    /**
     * Performs event command
     * @param commands is the latest command inputted by the user
     */
    public void performsEventsCommand(String[] commands) {
        String[] args = getTwoCommandArgs("/at", commands);
        Task eventTask = new Event(args[0], args[1]);
        taskList.add(eventTask);
        printLines(START_HORIZONTAL_LINE, ADDED_TASK_MESSAGE,
                COMMAND_INDENTATION + COMPLETION_INDENTATION + eventTask.toString(),
                String.format(LIST_SIZE_FORMAT, taskList.size()), END_HORIZONTAL_LINE);
    }

    /**
     * Performs deadline command.
     * @param commands is the latest command inputted by the user
     */
    public void performsDeadlineCommand(String[] commands) {
        String[] args = getTwoCommandArgs("/by", commands);
        Task deadlineTask = new Deadline(args[0], args[1]);
        taskList.add(deadlineTask);
        printLines(START_HORIZONTAL_LINE, ADDED_TASK_MESSAGE,
                COMMAND_INDENTATION + COMPLETION_INDENTATION + deadlineTask.toString(),
                String.format(LIST_SIZE_FORMAT, taskList.size()), END_HORIZONTAL_LINE);
    }

    /**
     * Concatenate the argument (in array).
     * @param command is last command inputted by the user (in array)
     * @return argument of the command in String
     */
    public String concatStrings(String[] command) {
        StringBuilder myStringBuilder = new StringBuilder();
        for (int i = 0; i < command.length; i++) {
            myStringBuilder.append(command[i]).append(" ");
        }
        myStringBuilder.deleteCharAt(myStringBuilder.length() - 1);
        return myStringBuilder.toString();
    }

    /**
     * Performs todo command.
     * @param command is the latest command inputted by the user
     */
    public void performsTodoCommand(String[] command) {
        String arg = concatStrings(Arrays.copyOfRange(command, 1, command.length));
        Task toDoTask = new Todo(arg);
        taskList.add(toDoTask);
        printLines(START_HORIZONTAL_LINE, ADDED_TASK_MESSAGE,
                COMMAND_INDENTATION + COMPLETION_INDENTATION + toDoTask.toString(),
                String.format(LIST_SIZE_FORMAT, taskList.size()), END_HORIZONTAL_LINE);
    }

    /**
     * Performs others command.
     * @param command is the last command entered by the user
     */
    public void performsOthersCommand(String command) {
        addCommandsEntered(command);
        printLines(START_HORIZONTAL_LINE, ADDED_MESSAGE + command, END_HORIZONTAL_LINE);
    }

    /**
     * Performs the command "done".
     * @param itemNum is the index of the task list
     */
    public void performsDoneCommand(int itemNum) {
        taskList.get(itemNum - 1).completeTask();
        printLines(START_HORIZONTAL_LINE, DONE_MESSAGE,
                COMMAND_INDENTATION + COMPLETION_INDENTATION + taskList.get(itemNum - 1).toString(), END_HORIZONTAL_LINE);
    }

    /**
     * Performs the command "list".
     */
    public void performsListCommand() {
        String formattedString = getTasks();
        printLines(START_HORIZONTAL_LINE, LIST_MESSAGE, COMMAND_INDENTATION + formattedString,
                END_HORIZONTAL_LINE);
    }

    /**
     * Performs the command "bye".
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
