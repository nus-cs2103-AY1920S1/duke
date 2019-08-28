package duke;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Implements the Duke chatbot.
 * @author Lim Yong Shen, Kevin
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Duke chatbot with the specified data file path.
     * @param filePath The specified data file path.
     */
    private Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch(DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke chatbot until the exit command is given.
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
     * Runs the Duke chatbot.
     * @param args Command line arguments (unused).
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
