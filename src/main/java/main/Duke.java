package main;

import command.Command;
import command.CommandCentre;
import task.Task;
import task.TaskList;
import utils.Parser;
import utils.Storage;
import utils.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class Duke {


    private static final String ROOT = "D:\\Gary\\Uni\\NUS\\1920SEM1\\CS2103T\\Practices\\duke";
    private static final String STORAGE_PATH = "\\data\\duke.txt";
    private static final boolean resetTaskList = false;

    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;
    private final CommandCentre commandCentre;

    public Duke() throws FileNotFoundException, ParseException {
        taskList = TaskList.newInstance();
        storage = new Storage(ROOT + STORAGE_PATH);
        ui = new Ui();
        parser = new Parser();
        commandCentre = new CommandCentre();
        initializeCommands();
        if (resetTaskList) {
            taskList.clear();
            storage.clearData();
        }
    }

    private void run() throws IOException {
        ui.printHelloMessage();

        while (parser.hasNext()) {
            String action = parser.getNextAction();
            ui.printDivider();
            boolean isSuccessful = commandCentre.execute(action);
            if (!isSuccessful) parser.nextLine();
            ui.printDivider();
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        new Duke().run();
    }


    private void initializeCommands() {
        commandCentre.register("bye", new Command() {
            @Override
            public void execute() {
                ui.printByeMessage();
            }
        });

        commandCentre.register("list", new Command() {
            @Override
            public void execute() {
                if (taskList.isEmpty()) {
                    ui.printEmptyTaskListMessage();
                } else {
                    ui.printTaskList(taskList.getTasks());
                }
            }
        });

        commandCentre.register("done", new Command() {
            @Override
            public void execute() throws IOException {
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
            public void execute() throws IOException {
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
            public void execute() throws IOException {
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
            public void execute() throws IOException {
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
            public void execute() throws IOException {
                String[] taskInfo = parser.parseEventDetail();
                if (taskInfo != null) {
                    Task newTask = taskList.addNewEventTask(taskInfo[0], taskInfo[1], false);
                    storage.updateData();
                    ui.printTaskAddedMessage(newTask, taskList.size());
                }
            }
        });

    }

}
