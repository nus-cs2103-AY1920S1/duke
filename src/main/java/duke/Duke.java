package duke;

import duke.commands.Command;
import duke.commands.CommandResult;

import duke.data.TaskList;

import duke.exceptions.DukeException;

import duke.parser.Parser;

import duke.storage.Storage;

import duke.ui.TextUi;

/**
 * Implements the Duke chat bot.
 * @author Lim Yong Shen, Kevin
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private TextUi ui;

    /**
     * Constructs a new Duke chat bot with the specified data file name.
     * @param fileName The specified data file name.
     */
    private Duke(String fileName) {
        ui = new TextUi();
        storage = new Storage(fileName);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Constructs a Duke instance (so that the application can be launched).
     */
    public Duke() {

    }

    /**
     * Runs the Duke chat bot until the exit command is given.
     */
    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                ui.showHorizontalBorder();
                Command command = Parser.parse(input);
                command.setTaskListToExecuteOn(tasks);
                CommandResult commandResult = command.execute();
                ui.showMessage(commandResult.getMessage());
                isExit = command.isExit();
                storage.save(tasks);
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            } finally {
                ui.showHorizontalBorder();
                ui.showEmptyLine();
            }
        }
        ui.close();
    }

    /**
     * Processes the specified input and returns the appropriate message.
     * @param input The specified input.
     * @return The appropriate message.
     */
    private String process(String input) {
        Command command = Parser.parse(input);
        assert tasks != null;
        command.setTaskListToExecuteOn(tasks);
        CommandResult commandResult = command.execute();
        return commandResult.getMessage();
    }

    /**
     * Executes the program.
     * @param args Command line arguments (unused).
     */
    public static void main(String[] args) {
        // new Duke("src/main/java/duke/data/tasks.txt").run();
        //Application.launch(args);
        new Duke("src/main/java/duke/data/tasks.txt");
    }

    /**
     * Returns Duke's response based on the specified input.
     * @param input The specified input.
     * @return Duke's response based on the specified input.
     */
    public String getResponse(String input) {
        return new Duke("src/main/java/duke/data/tasks.txt").process(input);
    }

}
