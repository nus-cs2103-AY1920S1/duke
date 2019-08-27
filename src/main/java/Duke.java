import slave.elements.*;
import slave.command.*;
import slave.exception.*;

/**
 * <h1>
 *     Welcome to Slave!
 * </h1>
 * <p>
 *     This Slave Program is a productivity application which helps
 * you to store and load tasks as well as letting you mark them
 * as done as you go about your daily routine!
 * </p>
 * @author Kalsyc
 * @version 1.0
 * @since 28 August 2019
 *
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * constructor which initialises storage and tasklist
     * @throws DukeException in case storage cannot be loaded
     */
    public Duke() throws DukeException {
        this.ui = new Ui();
        this.storage = new Storage("./data/duke.txt");
        this.taskList = new TaskList(this.storage.load(), this.storage);
        }

    /**
     * run method which contains the logic of the application. It takes in user input
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
     * Main method to drive the application
     * @param args Placeholder
     * @throws DukeException throws any exception that arises with running the application
     */
    public static void main(String[] args) throws DukeException{
        new Duke().run();
    }
}
