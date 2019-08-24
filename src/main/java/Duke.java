import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Duke {


    private Storage storage;
    private Ui ui;
    private TaskList tasks;


    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError(String.format(Messages.DESCRIPTION_FORMAT_EXCEPTION, "number"));
            }
        }
    }

    /**
     * Runs the commands entered by the user.
     * @param commands is the last command entered by the user
     * @throws DukeException If user inputted an invalid command
     */
//    public void executeCommand(String[] commands) throws DukeException, IOException {
//        if (commands[0].equals("bye")) {
//            if (commands.length == 1) {
//                myStorage.saveData(taskList);
//                performsByeCommand();
//            } else {
//                throw new DukeException(Ui.UNKNOWN_COMMAND_EXCEPTION);
//            }
//        } else if (commands[0].equals("list")) {
//            if (commands.length == 1) {
//                performsListCommand();
//            } else {
//                throw new DukeException(Ui.UNKNOWN_COMMAND_EXCEPTION);
//            }
//        } else if (commands[0].equals("done")) {
//            try {
//                if (commands.length == 2) {
//                    performsDoneCommand(Integer.parseInt(commands[1]));
//                } else if (commands.length == 1) {
//                    throw new DukeException(String.format(Ui.DESCRIPTION_MISSING_EXCEPTION, "done"));
//                } else {
//                    throw new DukeException(Ui.UNKNOWN_COMMAND_EXCEPTION);
//                }
//            } catch (NumberFormatException e) {
//                printLines(Ui.START_HORIZONTAL_LINE, String.format(Ui.DESCRIPTION_FORMAT_EXCEPTION, "done", "number"),
//                        Ui.END_HORIZONTAL_LINE);
//            }
//        } else if (commands[0].equals("todo")) {
//            if (commands.length > 1) {
//                performsTodoCommand(commands);
//            } else if (commands.length == 1){
//                throw new DukeException(String.format(Ui.DESCRIPTION_MISSING_EXCEPTION, "todo"));
//            }
//        } else if (commands[0].equals("deadline")) {
//            if (commands.length > 1) {
//                performsDeadlineCommand(commands);
//            } else if (commands.length == 1) {
//                throw new DukeException(String.format(Ui.DESCRIPTION_MISSING_EXCEPTION, "deadline"));
//            }
//        } else if (commands[0].equals("event")) {
//            if (commands.length > 1) {
//                performsEventsCommand(commands);
//            } else if (commands.length == 1) {
//                throw new DukeException(String.format(Ui.DESCRIPTION_MISSING_EXCEPTION, "event"));
//            }
//        } else if (commands[0].equals("delete")) {
//            try {
//                if (commands.length == 2) {
//                    performsDeleteCommand(Integer.parseInt(commands[1]));
//                } else if (commands.length == 1) {
//                    throw new DukeException(String.format(Ui.DESCRIPTION_MISSING_EXCEPTION, "delete"));
//                } else {
//                    throw new DukeException(Ui.UNKNOWN_COMMAND_EXCEPTION);
//                }
//            } catch (NumberFormatException e) {
//                printLines(Ui.START_HORIZONTAL_LINE, String.format(Ui.DESCRIPTION_FORMAT_EXCEPTION, "delete", "number"),
//                        Ui.END_HORIZONTAL_LINE);
//            }
//        } else {
//            throw new DukeException(Ui.UNKNOWN_COMMAND_EXCEPTION);
//        }
//    }

    /**
     * Performs delete command
     * @param itemNum is the index of the list which user wants to delete the task
     * @throws DukeException throws exception if the itemNum is invalid
     */
//    public void performsDeleteCommand(int itemNum) throws DukeException {
//        if (itemNum > taskList.size() || itemNum < 1) {
//            throw new DukeException(Ui.INVALID_SIZE_EXCEPTION);
//        }
//        Task removedTask = taskList.remove(itemNum - 1);
//        printLines(Ui.START_HORIZONTAL_LINE, Ui.DELETE_TASK_MESSAGE,
//                Ui.COMMAND_INDENTATION + Ui.COMPLETION_INDENTATION + removedTask.toString(),
//                String.format(Ui.LIST_SIZE_FORMAT, taskList.size()),
//                Ui.END_HORIZONTAL_LINE);
//    }




    /**
     * Performs event command
     * @param commands is the latest command inputted by the user
     * @throws DukeException the exception thrown by getTwoCommandArgs().
     */
//    public void performsEventsCommand(String[] commands) throws DukeException {
//        String[] args = GetArgumentsUtil.getTwoCommandArgs(1,"/at", commands);
//        Task eventTask = new Event(args[0], args[1]);
//        taskList.add(eventTask);
//        printLines(Ui.START_HORIZONTAL_LINE, Ui.ADDED_TASK_MESSAGE,
//                Ui.COMMAND_INDENTATION + Ui.COMPLETION_INDENTATION + eventTask.toString(),
//                String.format(Ui.LIST_SIZE_FORMAT, taskList.size()), Ui.END_HORIZONTAL_LINE);
//    }

    /**
     * Performs deadline command.
     * @param commands is the latest command inputted by the user
     * @throws DukeException the exception thrown by getTwoCommandArgs()
     */
//    public void performsDeadlineCommand(String[] commands) throws DukeException {
//        try {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN);
//            String[] args = GetArgumentsUtil.getTwoCommandArgs(1, "/by", commands);
//            LocalDateTime dateTime = LocalDateTime.parse(args[1], formatter);
//            Task deadlineTask = new Deadline(args[0], dateTime);
//            taskList.add(deadlineTask);
//            printLines(Ui.START_HORIZONTAL_LINE, Ui.ADDED_TASK_MESSAGE,
//                    Ui.COMMAND_INDENTATION + Ui.COMPLETION_INDENTATION + deadlineTask.toString(),
//                    String.format(Ui.LIST_SIZE_FORMAT, taskList.size()), Ui.END_HORIZONTAL_LINE);
//        } catch (DateTimeParseException e) {
//            printLines(Ui.START_HORIZONTAL_LINE, Ui.DATETIME_PARSE_EXCEPTION, Ui.END_HORIZONTAL_LINE);
//        }
//    }



    /**
     * Performs todo command.
     * @param command is the latest command inputted by the user
     */
//    public void performsTodoCommand(String[] command) {
//        String arg = GetArgumentsUtil.concatStrings(Arrays.copyOfRange(command, 1, command.length));
//        Task toDoTask = new Todo(arg);
//        taskList.add(toDoTask);
//        printLines(Ui.START_HORIZONTAL_LINE, Ui.ADDED_TASK_MESSAGE,
//                Ui.COMMAND_INDENTATION + Ui.COMPLETION_INDENTATION + toDoTask.toString(),
//                String.format(Ui.LIST_SIZE_FORMAT, taskList.size()), Ui.END_HORIZONTAL_LINE);
//    }

    /**
     * Performs the command "done".
     * @param itemNum is the index of the task list which user wants to change the status of the task
     * @throws DukeException throws exception if user provides an invalid itemNum.
     */
//    public void performsDoneCommand(int itemNum) throws DukeException {
//        if (itemNum > taskList.size() || itemNum < 1) {
//            throw new DukeException(Ui.INVALID_SIZE_EXCEPTION);
//        }
//        taskList.get(itemNum - 1).completeTask();
//        printLines(Ui.START_HORIZONTAL_LINE, Ui.DONE_MESSAGE,
//                Ui.COMMAND_INDENTATION + Ui.COMPLETION_INDENTATION + taskList.get(itemNum - 1).toString(),
//                Ui.END_HORIZONTAL_LINE);
//    }

    /**
     * Performs the command "list".
     */
//    public void performsListCommand() {
//        String formattedString = getTasks();
//        printLines(Ui.START_HORIZONTAL_LINE, Ui.LIST_MESSAGE, Ui.COMMAND_INDENTATION + formattedString,
//                Ui.END_HORIZONTAL_LINE);
//    }



}
