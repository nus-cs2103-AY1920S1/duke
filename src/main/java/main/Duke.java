package main;

import command.Command;
import command.CommandCentre;
import task.Task;
import task.TaskList;
import utils.Parser;
import utils.Storage;
import utils.Ui;

import java.util.List;


public class Duke {

    private static final String ROOT = "D:\\Gary\\Uni\\NUS\\1920SEM1\\CS2103T\\Practices\\duke";
    private static final String STORAGE_PATH = "\\data\\duke.txt";
    public static final String EXIT_MESSAGE = "main.Duke.EXIT_MESSAGE";
    private static final boolean RESET_TASK_LIST = false;

    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;
    private final CommandCentre commandCentre;
    private boolean isExiting;


    /**
     * Initializes TaskList, Storage, Ui, Parser objects. Initializes all
     * commands. Clears the existing TaskList in Storage if RESET_TASK_LIST
     * is true.
     */
    public Duke() {
        taskList = TaskList.newInstance();
        storage = new Storage(ROOT + STORAGE_PATH);
        ui = new Ui();
        commandCentre = new CommandCentre();
        commandCentre.setUi(ui);
        parser = new Parser();
        parser.setCommandCentre(commandCentre);
        isExiting = false;
        initializeCommands();
        if (RESET_TASK_LIST) {
            taskList.clear();
            storage.deleteData();
        }
    }


    /**
     * Commands Duke to respond based on the given user input.
     *
     * @param input The user input in this particular communication.
     */
    public String getResponse(String input) {
        parser.setUi(ui);
        String action = parser.getNextAction(input + "\n");
        if (action != null) {
            commandCentre.execute(action);
        }
        if (isExiting) {
            return EXIT_MESSAGE;
        }
        String output = ui.getOutputAndClearBuilder();
        return output;
    }

    /**
     * Gets a String of the Duke welcome message.
     *
     * @return The String containing the message.
     */
    public String getWelcomeMessage() {
        ui.printWelcomeMessage();
        String output = ui.getOutputAndClearBuilder();
        return output;
    }

    /**
     * Initialize all commands with their command names and store
     * them to command centre.
     */
    private void initializeCommands() {
        commandCentre.register("bye", () -> {
            ui.printByeMessage();
            isExiting = true;
        });

        commandCentre.register("help", () -> ui.printHelpMessage());

        commandCentre.register("list", () -> {
            if (taskList.isEmpty()) {
                ui.printEmptyTaskListMessage();
            } else {
                ui.printTaskList(taskList.getTasks(), Ui.LIST_ACTION_TITLE);
            }
        });

        commandCentre.register("done", () -> {
            Integer idx = parser.parseTaskIdx();
            if (idx != null) {
                taskList.markAsDone(idx);
                storage.updateData();
                ui.printMarkedAsDoneMessage(taskList.get(idx));

                commandCentre.addToHistory(() -> {
                    taskList.markAsNotDone(idx);
                    storage.updateData();
                    ui.printUndoMessage();
                    ui.printMarkedAsNotDoneMessage(taskList.get(idx));
                });
            }
        });

        commandCentre.register("delete", () -> {
            Integer idx = parser.parseTaskIdx();
            if (idx != null) {
                Task task = taskList.deleteTask(idx);
                storage.updateData();
                ui.printTaskDeletedMessage(task, taskList.size());

                commandCentre.addToHistory(() -> {
                    taskList.insertTask(task, idx);
                    storage.updateData();
                    ui.printUndoMessage();
                    ui.printTaskAddedMessage(task, taskList.size());
                });
            }
        });

        commandCentre.register("todo", () -> {
            String taskName = parser.parseTodoDetail();
            if (taskName != null) {
                Task task = taskList.addNewTodoTask(taskName, false);
                storage.updateData();
                ui.printTaskAddedMessage(task, taskList.size());
                commandCentre.addToHistory(generateDeleteTaskCommand(taskList.size()-1, true));
            }
        });

        commandCentre.register("deadline", () -> {
            String[] taskInfo = parser.parseDeadlineDetail();
            if (taskInfo != null) {
                Task newTask = taskList.addNewDeadlineTask(taskInfo[0], taskInfo[1], false);
                storage.updateData();
                ui.printTaskAddedMessage(newTask, taskList.size());
                commandCentre.addToHistory(generateDeleteTaskCommand(taskList.size()-1, true));
            }
        });

        commandCentre.register("event", () -> {
            String[] taskInfo = parser.parseEventDetail();
            if (taskInfo != null) {
                Task newTask = taskList.addNewEventTask(taskInfo[0], taskInfo[1], false);
                storage.updateData();
                ui.printTaskAddedMessage(newTask, taskList.size());
                commandCentre.addToHistory(generateDeleteTaskCommand(taskList.size()-1, true));
            }
        });

        commandCentre.register("find", () -> {
            String keyword = parser.parseFindKeyword();
            if (keyword != null) {
                List<Task> findResult = taskList.generateListByKeyword(keyword);
                ui.printTaskList(findResult, Ui.FIND_ACTION_TITLE);
            }
        });

        commandCentre.register("undo", () -> {
            commandCentre.undo();
        });
    }

    private Command generateDeleteTaskCommand(int idx, boolean isUndo) {
        return () -> {
            Task task = taskList.deleteTask(idx);
            storage.updateData();
            if (isUndo) ui.printUndoMessage();
            ui.printTaskDeletedMessage(task, taskList.size());
        };
    }

}
