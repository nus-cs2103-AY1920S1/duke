import slave.command.Command;
import slave.command.CommandType;

import slave.elements.Parser;
import slave.elements.Storage;
import slave.elements.TaskList;
import slave.elements.Ui;

import slave.exception.DukeException;

/**
 * <h1>
 *     Welcome to Slave.
 * </h1>
 * <p>
 *     This Slave Program is a productivity application which helps
 * you to store and load tasks as well as letting you mark them
 * as done as you go about your daily routine!
 * </p>
 *
 * @author Kalsyc
 * @version 1.0
 * @since 28 August 2019
 *
 */
public class Duke {

    private Ui ui;
    private TaskList taskList;

    /**
     * Constructor which initialises storage and taskList.
     *
     * @throws DukeException In case storage cannot be loaded
     */
    private Duke() throws DukeException {
        this.ui = new Ui();
        Storage storage = new Storage("./data/duke.txt");
        this.taskList = new TaskList(storage.load(), storage);
    }

    /**
     * Runs the logic of the application. It takes in user input
     * and parses the command before determining what type of command and execute
     * the command accordingly.
     */
    private void run() {
        this.ui.showWelcomeMessage();
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(this.taskList, this.ui);
                if (command.getCommandType().equals(CommandType.EXIT)) {
                    break;
                }
            } catch (DukeException e) {
                ui.showErrorMessage(e);
            }
        }
    }

    /**
     * Drives the application. Main method.
     *
     * @param args Placeholder
     * @throws DukeException Throws any exception that arises with running the application
     */
    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }
}
