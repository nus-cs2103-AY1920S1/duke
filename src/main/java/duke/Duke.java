package duke;

import java.io.IOException;
import java.text.ParseException;
import java.util.NoSuchElementException;

import duke.command.Command;
import duke.error.DukeException;
import duke.util.Storage;
import duke.util.Ui;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Main method.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
        System.exit(0);
    }

    /**
     * Constructor.
     */
    public Duke() {
        // TODO: Don't hardcode
        this.storage = new Storage("data/duke.txt"); 
        this.ui = new Ui();
        try {
            this.tasks = this.storage.load();    
        } catch (IOException e) {
            this.ui.printReadError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the duke program.
     */
    private void run() {
        this.ui.printWelcome();
        boolean shouldRun = true;
        while (shouldRun) {
            try {
                String strCommand = this.ui.readCommand();
                Command c = Parser.parse(strCommand);
                c.execute(this.tasks, this.ui, this.storage);
                shouldRun = !c.isExit();
            } catch (DukeException e) {
                this.ui.printError(e);
            } catch (ParseException e) {
                this.ui.print("Passed in an invalid date");
            } catch (NoSuchElementException e) {
                shouldRun = false;
            }
        }
    }

    /**
     * Handles a response from the input.
     *
     * @param input String
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(this.tasks, this.ui, this.storage);
        } catch (DukeException e) {
            return this.ui.stringifyError(e);
        } catch (ParseException e) {
            return "Passed in an invalid date";
        } 
    }
}
