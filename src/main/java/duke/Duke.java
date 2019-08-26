package duke;

import java.io.IOException;
import java.text.ParseException;
import duke.error.DukeException;
import duke.command.Command;
//CHECKSTYLE:OFF
import duke.util.*;
//CHECKSTYLE:ON

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
    }

    /**
     * Constructor.
     */
    public Duke() {
        // TODO: Don't hardcode
        this.storage = new Storage("/Users/joshuawong/Documents/NUS/Y2S1/CS2103/duke/data/duke.txt"); 
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
                ui.printError(e);
            } catch (ParseException e) {
                ui.print("Passed in an invalid date");
            } 
        }
    }
}
