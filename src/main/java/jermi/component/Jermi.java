package jermi.component;

import jermi.command.Command;
import jermi.exception.JermiException;

/**
 * The Jermi program implements an application that keeps track of the user's tasks.
 *
 * @author Jermy
 * @version v2.0
 * @since 2019-08-25
 */
public class Jermi {
    /** Ui object reference. */
    private Ui ui;
    /** Parser object reference. */
    private Parser parser;
    /** TaskList object reference. */
    private TaskList taskList;
    /** Storage object reference. */
    private Storage storage;
    /** ExceptionHandler object reference. */
    private ExceptionHandler exceptionHandler;

    /**
     * Private constructor for class.
     * Initialises {@link Ui}, {@link Parser}, {@link TaskList} and {@link ExceptionHandler}.
     */
    public Jermi() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.taskList = new TaskList();
        this.exceptionHandler = new ExceptionHandler(this.ui);
    }

    /**
     * Initialises {@link Storage}.
     *
     * @param pathname Pathname of storage file.
     * @return Welcome message if Storage has been successfully initialised, else error message.
     */
    public String initialiseStorage(String pathname) {
        try {
            this.storage = new Storage(pathname, this.taskList);
            return this.ui.greet();
        } catch (JermiException e) {
            return this.exceptionHandler.handleCheckedExceptions(e);
        } catch (Exception e) {
            return this.exceptionHandler.handleUncheckedExceptions(e);
        }
    }

    /**
     * Outputs the response of Jermi.
     *
     * @param input User input.
     * @return Response of Jermi.
     */
    public String getResponse(String input, boolean[] shouldExit) {
        try {
            String inputCommand = this.ui.readCommand(input);
            String inputDetails = this.ui.readDetails(input);
            Command command = this.parser.parse(inputCommand, inputDetails);
            shouldExit[0] = command.shouldExit();
            return command.execute(this.taskList, this.ui, this.storage);
        } catch (JermiException e) {
            return this.exceptionHandler.handleCheckedExceptions(e);
        } catch (Exception e) {
            return this.exceptionHandler.handleUncheckedExceptions(e);
        }
    }
}
