import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {


    private List<Task> taskList;
    private Scanner myScanner;

    public static final String DATETIME_PATTERN = "dd/MM/yyyy HHmm";

    private Parser myParser;

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
        myParser = new Parser();
        try {
            taskList.addAll(myParser.setup());
            displayGreetingMessage();
            while (myScanner.hasNextLine()) {
                String[] userInput = readCommand();
                try {
                    executeCommand(userInput);
                } catch (DukeException e) {
                    printLines(Messages.START_HORIZONTAL_LINE, e.getMessage(), Messages.END_HORIZONTAL_LINE);
                }
            }
        } catch (FileNotFoundException e) {
            printLines(Messages.START_HORIZONTAL_LINE, e.getMessage(), Messages.END_HORIZONTAL_LINE);
        } catch (IOException e1) {
            printLines(Messages.START_HORIZONTAL_LINE, e1.getMessage(), Messages.END_HORIZONTAL_LINE);
        } catch (DukeException e2) {
            printLines(Messages.START_HORIZONTAL_LINE, e2.getMessage(), Messages.END_HORIZONTAL_LINE);
        }
    }



    /**
     * Formats the welcome message and print it.
     */
    public void displayGreetingMessage() {
        printLines(Messages.START_HORIZONTAL_LINE, Messages.GREETING_MESSAGE, Messages.END_HORIZONTAL_LINE);
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
        return myScanner.nextLine().trim().split("\\s+");
    }

    /**
     * Runs the commands entered by the user.
     * @param commands is the last command entered by the user
     * @throws DukeException If user inputted an invalid command
     */
    public void executeCommand(String[] commands) throws DukeException, IOException {
        if (commands[0].equals("bye")) {
            if (commands.length == 1) {
                myParser.saveData(taskList);
                performsByeCommand();
            } else {
                throw new DukeException(Messages.UNKNOWN_COMMAND_EXCEPTION);
            }
        } else if (commands[0].equals("list")) {
            if (commands.length == 1) {
                performsListCommand();
            } else {
                throw new DukeException(Messages.UNKNOWN_COMMAND_EXCEPTION);
            }
        } else if (commands[0].equals("done")) {
            try {
                if (commands.length == 2) {
                    performsDoneCommand(Integer.parseInt(commands[1]));
                } else if (commands.length == 1) {
                    throw new DukeException(String.format(Messages.DESCRIPTION_MISSING_EXCEPTION, "done"));
                } else {
                    throw new DukeException(Messages.UNKNOWN_COMMAND_EXCEPTION);
                }
            } catch (NumberFormatException e) {
                printLines(Messages.START_HORIZONTAL_LINE, String.format(Messages.DESCRIPTION_FORMAT_EXCEPTION, "done", "number"),
                        Messages.END_HORIZONTAL_LINE);
            }
        } else if (commands[0].equals("todo")) {
            if (commands.length > 1) {
                performsTodoCommand(commands);
            } else if (commands.length == 1){
                throw new DukeException(String.format(Messages.DESCRIPTION_MISSING_EXCEPTION, "todo"));
            }
        } else if (commands[0].equals("deadline")) {
            if (commands.length > 1) {
                performsDeadlineCommand(commands);
            } else if (commands.length == 1) {
                throw new DukeException(String.format(Messages.DESCRIPTION_MISSING_EXCEPTION, "deadline"));
            }
        } else if (commands[0].equals("event")) {
            if (commands.length > 1) {
                performsEventsCommand(commands);
            } else if (commands.length == 1) {
                throw new DukeException(String.format(Messages.DESCRIPTION_MISSING_EXCEPTION, "event"));
            }
        } else if (commands[0].equals("delete")) {
            try {
                if (commands.length == 2) {
                    performsDeleteCommand(Integer.parseInt(commands[1]));
                } else if (commands.length == 1) {
                    throw new DukeException(String.format(Messages.DESCRIPTION_MISSING_EXCEPTION, "delete"));
                } else {
                    throw new DukeException(Messages.UNKNOWN_COMMAND_EXCEPTION);
                }
            } catch (NumberFormatException e) {
                printLines(Messages.START_HORIZONTAL_LINE, String.format(Messages.DESCRIPTION_FORMAT_EXCEPTION, "delete", "number"),
                        Messages.END_HORIZONTAL_LINE);
            }
        } else {
            throw new DukeException(Messages.UNKNOWN_COMMAND_EXCEPTION);
        }
    }

    /**
     * Performs delete command
     * @param itemNum is the index of the list which user wants to delete the task
     * @throws DukeException throws exception if the itemNum is invalid
     */
    public void performsDeleteCommand(int itemNum) throws DukeException {
        if (itemNum > taskList.size() || itemNum < 1) {
            throw new DukeException(Messages.INVALID_SIZE_EXCEPTION);
        }
        Task removedTask = taskList.remove(itemNum - 1);
        printLines(Messages.START_HORIZONTAL_LINE, Messages.DELETE_TASK_MESSAGE,
                Messages.COMMAND_INDENTATION + Messages.COMPLETION_INDENTATION + removedTask.toString(),
                String.format(Messages.LIST_SIZE_FORMAT, taskList.size()),
                Messages.END_HORIZONTAL_LINE);
    }




    /**
     * Performs event command
     * @param commands is the latest command inputted by the user
     * @throws DukeException the exception thrown by getTwoCommandArgs().
     */
    public void performsEventsCommand(String[] commands) throws DukeException {
        String[] args = GetArgumentsUtil.getTwoCommandArgs(1,"/at", commands);
        Task eventTask = new Event(args[0], args[1]);
        taskList.add(eventTask);
        printLines(Messages.START_HORIZONTAL_LINE, Messages.ADDED_TASK_MESSAGE,
                Messages.COMMAND_INDENTATION + Messages.COMPLETION_INDENTATION + eventTask.toString(),
                String.format(Messages.LIST_SIZE_FORMAT, taskList.size()), Messages.END_HORIZONTAL_LINE);
    }

    /**
     * Performs deadline command.
     * @param commands is the latest command inputted by the user
     * @throws DukeException the exception thrown by getTwoCommandArgs()
     */
    public void performsDeadlineCommand(String[] commands) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN);
            String[] args = GetArgumentsUtil.getTwoCommandArgs(1, "/by", commands);
            LocalDateTime dateTime = LocalDateTime.parse(args[1], formatter);
            Task deadlineTask = new Deadline(args[0], dateTime);
            taskList.add(deadlineTask);
            printLines(Messages.START_HORIZONTAL_LINE, Messages.ADDED_TASK_MESSAGE,
                    Messages.COMMAND_INDENTATION + Messages.COMPLETION_INDENTATION + deadlineTask.toString(),
                    String.format(Messages.LIST_SIZE_FORMAT, taskList.size()), Messages.END_HORIZONTAL_LINE);
        } catch (DateTimeParseException e) {
            printLines(Messages.START_HORIZONTAL_LINE, Messages.DATETIME_PARSE_EXCEPTION, Messages.END_HORIZONTAL_LINE);
        }
    }



    /**
     * Performs todo command.
     * @param command is the latest command inputted by the user
     */
    public void performsTodoCommand(String[] command) {
        String arg = GetArgumentsUtil.concatStrings(Arrays.copyOfRange(command, 1, command.length));
        Task toDoTask = new Todo(arg);
        taskList.add(toDoTask);
        printLines(Messages.START_HORIZONTAL_LINE, Messages.ADDED_TASK_MESSAGE,
                Messages.COMMAND_INDENTATION + Messages.COMPLETION_INDENTATION + toDoTask.toString(),
                String.format(Messages.LIST_SIZE_FORMAT, taskList.size()), Messages.END_HORIZONTAL_LINE);
    }

    /**
     * Performs the command "done".
     * @param itemNum is the index of the task list which user wants to change the status of the task
     * @throws DukeException throws exception if user provides an invalid itemNum.
     */
    public void performsDoneCommand(int itemNum) throws DukeException {
        if (itemNum > taskList.size() || itemNum < 1) {
            throw new DukeException(Messages.INVALID_SIZE_EXCEPTION);
        }
        taskList.get(itemNum - 1).completeTask();
        printLines(Messages.START_HORIZONTAL_LINE, Messages.DONE_MESSAGE,
                Messages.COMMAND_INDENTATION + Messages.COMPLETION_INDENTATION + taskList.get(itemNum - 1).toString(),
                Messages.END_HORIZONTAL_LINE);
    }

    /**
     * Performs the command "list".
     */
    public void performsListCommand() {
        String formattedString = getTasks();
        printLines(Messages.START_HORIZONTAL_LINE, Messages.LIST_MESSAGE, Messages.COMMAND_INDENTATION + formattedString,
                Messages.END_HORIZONTAL_LINE);
    }

    /**
     * Performs the command "bye".
     */
    public void performsByeCommand() {
        printLines(Messages.START_HORIZONTAL_LINE, Messages.BYE_MESSAGE, Messages.END_HORIZONTAL_LINE);
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
                        .append(Messages.COMMAND_INDENTATION);
            } else if (i == taskList.size() - 1) {
                myStringBuilder.append(i + 1).append(".").append(taskList.get(i));
            }
        }
        return myStringBuilder.toString();
    }
}
