package main;

import command.Command;
import command.CommandCentre;
import task.Task;
import task.TaskList;
import utils.Parser;
import utils.Storage;
import utils.Ui;

import java.util.List;
import java.util.Scanner;


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
     * <p>
     * Initializes TaskList, Storage, Ui, Parser objects. Initializes all
     * commands. Clears the existing TaskList in Storage if RESET_TASK_LIST
     * is true.
     * </p>
     */
    public Duke() {
        taskList = TaskList.newInstance();
        storage = new Storage(ROOT + STORAGE_PATH);
        ui = Ui.getInstance();
        parser = new Parser();
        commandCentre = new CommandCentre();
        isExiting = false;
        initializeCommands();
        if (RESET_TASK_LIST) {
            taskList.clear();
            storage.clearData();
        }
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        parser.setScanner(new Scanner(input));
        String action = parser.getNextAction();
        ui.printDivider();
        commandCentre.execute(action);
        ui.printDivider();
        if (isExiting) {
            return EXIT_MESSAGE;
        }
        String output = ui.getOutput();
        ui.resetOutputBuilder();
        return output;
    }

    /**
     * Gets a String of the Duke welcome message.
     *
     * @return The String containing the message.
     */
    public String getWelcomeMessage() {
        ui.printWelcomeMessage();
        String output = ui.getOutput();
        ui.resetOutputBuilder();
        return output;
    }

    /**
     * Initialize all commands with their command names and store
     * them to command centre.
     */
    private void initializeCommands() {
        commandCentre.register("bye", new Command() {
            @Override
            public void execute() {
                ui.printByeMessage();
                isExiting = true;
            }
        });

        commandCentre.register("help", new Command() {
            @Override
            protected void execute() {
                ui.printHelpMessage();
            }
        });

        commandCentre.register("list", new Command() {
            @Override
            public void execute() {
                if (taskList.isEmpty()) {
                    ui.printEmptyTaskListMessage();
                } else {
                    ui.printTaskList(taskList.getTasks(), Ui.LIST_ACTION_TITLE);
                }
            }
        });

        commandCentre.register("done", new Command() {
            @Override
            public void execute() {
                Integer idx = parser.getTaskIdx();
                if (idx != null) {
                    taskList.markAsDone(idx);
                    storage.updateData();
                    ui.printMarkedAsDoneMessage(taskList.get(idx));
                }
            }
        });

        commandCentre.register("delete", new Command() {
            @Override
            public void execute() {
                Integer idx = parser.getTaskIdx();
                if (idx != null) {
                    Task task = taskList.deleteTask(idx);
                    storage.updateData();
                    ui.printTaskDeletedMessage(task, taskList.size());
                }
            }
        });

        commandCentre.register("todo", new Command() {
            @Override
            public void execute() {
                String taskName = parser.parseTodoDetail();
                if (taskName != null) {
                    Task newTask = taskList.addNewTodoTask(taskName, false);
                    storage.updateData();
                    ui.printTaskAddedMessage(newTask, taskList.size());
                }
            }
        });

        commandCentre.register("deadline", new Command() {
            @Override
            public void execute() {
                String[] taskInfo = parser.parseDeadlineDetail();
                if (taskInfo != null) {
                    Task newTask = taskList.addNewDeadlineTask(taskInfo[0], taskInfo[1], false);
                    storage.updateData();
                    ui.printTaskAddedMessage(newTask, taskList.size());
                }
            }
        });

        commandCentre.register("event", new Command() {
            @Override
            public void execute() {
                String[] taskInfo = parser.parseEventDetail();
                if (taskInfo != null) {
                    Task newTask = taskList.addNewEventTask(taskInfo[0], taskInfo[1], false);
                    storage.updateData();
                    ui.printTaskAddedMessage(newTask, taskList.size());
                }
            }
        });

        commandCentre.register("find", new Command() {
            @Override
            public void execute() {
                String keyword = parser.parseFindKeyword();
                if (keyword != null) {
                    List<Task> findResult = taskList.generateListByKeyword(keyword);
                    ui.printTaskList(findResult, Ui.FIND_ACTION_TITLE);
                }
            }
        });
    }

}
