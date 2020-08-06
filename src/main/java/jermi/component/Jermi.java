package jermi.component;

import static jermi.misc.Constant.SHOULD_EXIT_INDEX;

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
    /** Formatter object reference. */
    private Formatter formatter;
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
     * Initialises {@link Formatter}, {@link Parser}, {@link TaskList} and {@link ExceptionHandler}.
     */
    public Jermi() {
        this.formatter = new Formatter();
        this.parser = new Parser();
        this.taskList = new TaskList();
        this.exceptionHandler = new ExceptionHandler(this.formatter);
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
            return this.formatter.greet();
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
     * @param shouldExit Indicator for if the program should close.
     * @return Response of Jermi.
     */
    public String getResponse(String input, boolean[] shouldExit) {
        try {
            String inputCommand = this.formatter.readCommand(input);
            String inputDetails = this.formatter.readDetails(input);
            Command command = this.parser.parse(inputCommand, inputDetails);
            shouldExit[SHOULD_EXIT_INDEX] = command.shouldExit();
            return command.execute(this.taskList, this.formatter, this.storage);
        } catch (JermiException e) {
            return this.exceptionHandler.handleCheckedExceptions(e);
        } catch (Exception e) {
            return this.exceptionHandler.handleUncheckedExceptions(e);
        }
    }
}
