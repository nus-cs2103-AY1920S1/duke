package jermi;

import jermi.command.Command;
import jermi.component.Ui;
import jermi.component.Parser;
import jermi.component.TaskList;
import jermi.component.Storage;
import jermi.component.ExceptionHandler;
import jermi.exception.JermiException;

/**
 * The Client program manages the workflow of the application.
 */
public class Client {
    /** Client object reference */
    private static Client client = null;
    /** Ui object reference */
    private Ui ui;
    /** Parser object reference */
    private Parser parser;
    /** TaskList object reference */
    private TaskList taskList;
    /** Storage object reference */
    private Storage storage;
    /** ExceptionHandler object reference */
    private ExceptionHandler exceptionHandler;

    /**
     * Private constructor for class.
     * Initialises {@link Ui}, {@link Parser}, {@link TaskList} and {@link ExceptionHandler}.
     */
    private Client() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.taskList = new TaskList();
        this.exceptionHandler = new ExceptionHandler(this.ui);
    }

    /**
     * Initialises {@link Client}.
     *
     * @return New Client object if one has not been initialised before, else existing Client object.
     */
    public static Client initialise() {
        if (client == null) {
            client = new Client();
        } else {
            client.ui.echo("Client has already been initialised.");
        }
        return client;
    }

    /**
     * Initialises {@link Storage}.
     *
     * @param pathname Pathname of storage file.
     * @return true if Storage has been successfully initialised, else false.
     */
    private boolean initialiseStorage(String pathname) {
        boolean shouldContinue = false;
        try {
            this.storage = new Storage(pathname, this.taskList);
            this.ui.greet();
            shouldContinue = true;
        } catch (JermiException e) {
            this.exceptionHandler.handleCheckedExceptions(e);
            this.ui.runFail();
        } catch (Exception e) {
            this.exceptionHandler.handleUncheckedExceptions(e);
            this.ui.runFail();
        }
        return shouldContinue;
    }

    /**
     * Runs the client.
     *
     * @param pathname Path name of storage file.
     */
    public void run(String pathname) {
        boolean shouldContinue = initialiseStorage(pathname);

        while (shouldContinue) {
            try {
                String inputCommand = this.ui.readCommand();
                String inputDetails = this.ui.readDetails();
                Command command = this.parser.parse(inputCommand, inputDetails);
                command.execute(this.taskList, this.ui, this.storage);
                shouldContinue = !command.shouldExit();
            } catch (JermiException e) {
                this.exceptionHandler.handleCheckedExceptions(e);
                shouldContinue = true;
            } catch (Exception e) {
                this.exceptionHandler.handleUncheckedExceptions(e);
                shouldContinue = false;
            }
        }

    }
}
