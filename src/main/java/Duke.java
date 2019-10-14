package duke.driver;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a instance of Duke which is the main driver of the program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean exit;

    public Duke() {
        this("test.txt");
    }

    /**
     * Creates the Duke object which has a db file tagged to it.
     * 
     * @param filename the filename of the file to be referenced to
     */

    public Duke(String filename) {
        ui = new Ui();
        storage = new Storage(filename);
        tasks = new TaskList(storage.load());
        this.exit = false;
    }

    /**
     * Runs the program.
     */
    public void run() {
        ui.print(ui.welcome());
        String fullCommand;
        while (ui.hasNext() && !(fullCommand = ui.readCommand()).equals("bye")) {
            ui.printLine();
            try {
                Command c = Parser.parse(fullCommand);
                ui.print(c.execute(tasks, ui, storage));
            } catch (DukeException e) {
                ui.errorMessage(e);
            }
            ui.printLine();
        }
        ui.byeMessage();
    }

    /**
     * The main driver of the program. Takes in the file to be referenced as a
     * command line argument. If no file is given, then the default file "test.txt"
     * is used.
     *
     * @param args command line arguments that could include the filename of the
     *             file to be used
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            new Duke().run();
        } else {
            new Duke(args[0]).run();
        }
    }

    /**
     * Takes in a user input and returns a string representation of the response
     * after executing the input.
     * 
     * @param input a string representing the input of the user
     * @return a string representing the response of Duke
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            this.exit = c.isExit();
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.errorMessage(e);
        }
    }

    /**
     * Return the exit state of the program. If true, means the user has issued the
     * command to end the program. Else, the program will keep running.
     * 
     * @return a boolean value of the exit state of the program.
     */
    public boolean isExit() {
        return this.exit;
    }
}