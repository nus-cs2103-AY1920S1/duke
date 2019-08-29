package duke;

import duke.commands.Command;
import duke.commands.CommandResult;

import duke.data.TaskList;

import duke.exceptions.DukeException;

import duke.parser.Parser;

import duke.storage.Storage;

import duke.ui.Ui;

/**
 * Implements the Duke chat bot.
 * @author Lim Yong Shen, Kevin
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Duke chat bot with the specified data file name.
     * @param fileName The specified data file name.
     */
    private Duke(String fileName) {
        ui = new Ui();
        storage = new Storage(fileName);
        try {
            tasks = storage.load();
        } catch(DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
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
     * Runs the Duke chat bot.
     * @param args Command line arguments (unused).
     */
    public static void main(String[] args) {
        new Duke("/Users/lyskevin/Desktop/Y2 Sem 1/CS2103T/duke/src/main/java/duke/data/tasks.txt").run();
    }

}
