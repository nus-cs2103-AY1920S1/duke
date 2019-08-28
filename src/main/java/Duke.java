import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.text.ParseException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the entire logic of the program. showWelcome() displays the duke logo and greets
     * the user. isExit boolean ensures that the while loop doesn't end until the user input of
     * "bye" is encountered. User's input will be read through the ui and then parsed to give
     * a Command object. The specific command will then be executed.
     * <p>
     * This method has try and catch blocks within to help with catching both DukeException
     * and ParseException
     *
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Runs the main method to load the duke.txt file into the program and then calls the run method
     * @param args
     * @throws DukeException
     * @throws ParseException
     */
    public static void main(String[] args) throws DukeException, ParseException{
        new Duke("/Users/lawnce/Desktop/duke/data/duke.txt").run();
    }
}
