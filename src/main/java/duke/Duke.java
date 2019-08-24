package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

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
     * Runs the duke.Duke program.
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
//    public void executeCommand(String[] commands) throws duke.exception.DukeException, IOException {
//        if (commands[0].equals("bye")) {
//            if (commands.length == 1) {
//                myStorage.saveData(taskList);
//                performsByeCommand();
//            } else {
//                throw new duke.exception.DukeException(duke.ui.Ui.UNKNOWN_COMMAND_EXCEPTION);
//            }
//        } else if (commands[0].equals("list")) {
//            if (commands.length == 1) {
//                performsListCommand();
//            } else {
//                throw new duke.exception.DukeException(duke.ui.Ui.UNKNOWN_COMMAND_EXCEPTION);
//            }
//        } else if (commands[0].equals("done")) {
//            try {
//                if (commands.length == 2) {
//                    performsDoneCommand(Integer.parseInt(commands[1]));
//                } else if (commands.length == 1) {
//                    throw new duke.exception.DukeException(String.format(duke.ui.Ui.DESCRIPTION_MISSING_EXCEPTION, "done"));
//                } else {
//                    throw new duke.exception.DukeException(duke.ui.Ui.UNKNOWN_COMMAND_EXCEPTION);
//                }
//            } catch (NumberFormatException e) {
//                printLines(duke.ui.Ui.START_HORIZONTAL_LINE, String.format(duke.ui.Ui.DESCRIPTION_FORMAT_EXCEPTION, "done", "number"),
//                        duke.ui.Ui.END_HORIZONTAL_LINE);
//            }
//        } else if (commands[0].equals("todo")) {
//            if (commands.length > 1) {
//                performsTodoCommand(commands);
//            } else if (commands.length == 1){
//                throw new duke.exception.DukeException(String.format(duke.ui.Ui.DESCRIPTION_MISSING_EXCEPTION, "todo"));
//            }
//        } else if (commands[0].equals("deadline")) {
//            if (commands.length > 1) {
//                performsDeadlineCommand(commands);
//            } else if (commands.length == 1) {
//                throw new duke.exception.DukeException(String.format(duke.ui.Ui.DESCRIPTION_MISSING_EXCEPTION, "deadline"));
//            }
//        } else if (commands[0].equals("event")) {
//            if (commands.length > 1) {
//                performsEventsCommand(commands);
//            } else if (commands.length == 1) {
//                throw new duke.exception.DukeException(String.format(duke.ui.Ui.DESCRIPTION_MISSING_EXCEPTION, "event"));
//            }
//        } else if (commands[0].equals("delete")) {
//            try {
//                if (commands.length == 2) {
//                    performsDeleteCommand(Integer.parseInt(commands[1]));
//                } else if (commands.length == 1) {
//                    throw new duke.exception.DukeException(String.format(duke.ui.Ui.DESCRIPTION_MISSING_EXCEPTION, "delete"));
//                } else {
//                    throw new duke.exception.DukeException(duke.ui.Ui.UNKNOWN_COMMAND_EXCEPTION);
//                }
//            } catch (NumberFormatException e) {
//                printLines(duke.ui.Ui.START_HORIZONTAL_LINE, String.format(duke.ui.Ui.DESCRIPTION_FORMAT_EXCEPTION, "delete", "number"),
//                        duke.ui.Ui.END_HORIZONTAL_LINE);
//            }
//        } else {
//            throw new duke.exception.DukeException(duke.ui.Ui.UNKNOWN_COMMAND_EXCEPTION);
//        }
//    }

    /**
     * Performs delete command
     * @param itemNum is the index of the list which user wants to delete the task
     * @throws DukeException throws exception if the itemNum is invalid
     */
//    public void performsDeleteCommand(int itemNum) throws duke.exception.DukeException {
//        if (itemNum > taskList.size() || itemNum < 1) {
//            throw new duke.exception.DukeException(duke.ui.Ui.INVALID_SIZE_EXCEPTION);
//        }
//        duke.task.Task removedTask = taskList.remove(itemNum - 1);
//        printLines(duke.ui.Ui.START_HORIZONTAL_LINE, duke.ui.Ui.DELETE_TASK_MESSAGE,
//                duke.ui.Ui.COMMAND_INDENTATION + duke.ui.Ui.COMPLETION_INDENTATION + removedTask.toString(),
//                String.format(duke.ui.Ui.LIST_SIZE_FORMAT, taskList.size()),
//                duke.ui.Ui.END_HORIZONTAL_LINE);
//    }




    /**
     * Performs event command
     * @param commands is the latest command inputted by the user
     * @throws DukeException the exception thrown by getTwoCommandArgs().
     */
//    public void performsEventsCommand(String[] commands) throws duke.exception.DukeException {
//        String[] args = duke.shared.GetArgumentsUtil.getTwoCommandArgs(1,"/at", commands);
//        duke.task.Task eventTask = new duke.task.Event(args[0], args[1]);
//        taskList.add(eventTask);
//        printLines(duke.ui.Ui.START_HORIZONTAL_LINE, duke.ui.Ui.ADDED_TASK_MESSAGE,
//                duke.ui.Ui.COMMAND_INDENTATION + duke.ui.Ui.COMPLETION_INDENTATION + eventTask.toString(),
//                String.format(duke.ui.Ui.LIST_SIZE_FORMAT, taskList.size()), duke.ui.Ui.END_HORIZONTAL_LINE);
//    }

    /**
     * Performs deadline command.
     * @param commands is the latest command inputted by the user
     * @throws DukeException the exception thrown by getTwoCommandArgs()
     */
//    public void performsDeadlineCommand(String[] commands) throws duke.exception.DukeException {
//        try {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN);
//            String[] args = duke.shared.GetArgumentsUtil.getTwoCommandArgs(1, "/by", commands);
//            LocalDateTime dateTime = LocalDateTime.parse(args[1], formatter);
//            duke.task.Task deadlineTask = new duke.task.Deadline(args[0], dateTime);
//            taskList.add(deadlineTask);
//            printLines(duke.ui.Ui.START_HORIZONTAL_LINE, duke.ui.Ui.ADDED_TASK_MESSAGE,
//                    duke.ui.Ui.COMMAND_INDENTATION + duke.ui.Ui.COMPLETION_INDENTATION + deadlineTask.toString(),
//                    String.format(duke.ui.Ui.LIST_SIZE_FORMAT, taskList.size()), duke.ui.Ui.END_HORIZONTAL_LINE);
//        } catch (DateTimeParseException e) {
//            printLines(duke.ui.Ui.START_HORIZONTAL_LINE, duke.ui.Ui.DATETIME_PARSE_EXCEPTION, duke.ui.Ui.END_HORIZONTAL_LINE);
//        }
//    }



    /**
     * Performs todo command.
     * @param command is the latest command inputted by the user
     */
//    public void performsTodoCommand(String[] command) {
//        String arg = duke.shared.GetArgumentsUtil.concatStrings(Arrays.copyOfRange(command, 1, command.length));
//        duke.task.Task toDoTask = new duke.task.Todo(arg);
//        taskList.add(toDoTask);
//        printLines(duke.ui.Ui.START_HORIZONTAL_LINE, duke.ui.Ui.ADDED_TASK_MESSAGE,
//                duke.ui.Ui.COMMAND_INDENTATION + duke.ui.Ui.COMPLETION_INDENTATION + toDoTask.toString(),
//                String.format(duke.ui.Ui.LIST_SIZE_FORMAT, taskList.size()), duke.ui.Ui.END_HORIZONTAL_LINE);
//    }

    /**
     * Performs the command "done".
     * @param itemNum is the index of the task list which user wants to change the status of the task
     * @throws DukeException throws exception if user provides an invalid itemNum.
     */
//    public void performsDoneCommand(int itemNum) throws duke.exception.DukeException {
//        if (itemNum > taskList.size() || itemNum < 1) {
//            throw new duke.exception.DukeException(duke.ui.Ui.INVALID_SIZE_EXCEPTION);
//        }
//        taskList.get(itemNum - 1).completeTask();
//        printLines(duke.ui.Ui.START_HORIZONTAL_LINE, duke.ui.Ui.DONE_MESSAGE,
//                duke.ui.Ui.COMMAND_INDENTATION + duke.ui.Ui.COMPLETION_INDENTATION + taskList.get(itemNum - 1).toString(),
//                duke.ui.Ui.END_HORIZONTAL_LINE);
//    }

    /**
     * Performs the command "list".
     */
//    public void performsListCommand() {
//        String formattedString = getTasks();
//        printLines(duke.ui.Ui.START_HORIZONTAL_LINE, duke.ui.Ui.LIST_MESSAGE, duke.ui.Ui.COMMAND_INDENTATION + formattedString,
//                duke.ui.Ui.END_HORIZONTAL_LINE);
//    }



}
