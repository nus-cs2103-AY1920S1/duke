package duke.core;

import duke.command.Command;
import duke.exception.CorruptedTasksException;
import duke.exception.DukeException;
import duke.ui.GuiPrinter;
import duke.ui.Printer;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;

public class DukeModel {

    private static final String URL_STORAGE = "duke.txt";

    private DukeResponder responder;
    private Printer printer;
    private Storage storage;
    private TaskList tasks;

    /**
     * Initializes the Duke class.
     * This method should be executed after the creation
     * of the {@link DukeController} object and before the
     * execution of {@link DukeModel#getResponse getResponse}.
     *
     * @param controller the main window for the Duke GUI
     */
    public void initialize(DukeController controller) {
        this.responder = new DukeResponder();
        this.printer = new GuiPrinter(controller);
        this.storage = new Storage(DukeModel.URL_STORAGE);

        try {
            this.tasks = this.storage.loadTasks();
        } catch (CorruptedTasksException e) {
            this.printer.printDuke(this.responder.getErrorMessage(e));
            this.tasks = new TaskList();
        }

        this.printer.printDuke(this.responder.getWelcomeMessage());
    }

    /**
     * Get a response to a given command.
     * This method returns the response from Duke
     * given the command.
     *
     * @param fullCommand the command to be executed
     */
    public void getResponse(String fullCommand) {
        try {
            this.printer.printUser(fullCommand);
            Command command = Parser.parseCommand(fullCommand);
            String message = command.execute(this.tasks, this.responder, this.storage);
            this.printer.printDuke(message);
        } catch (DukeException e) {
            this.printer.printDuke(this.responder.getErrorMessage(e));
        }
    }

}
