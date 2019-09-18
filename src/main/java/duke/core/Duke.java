package duke.core;

/**
 * This program reads in command, adds new tasks into the task lists, changes the task
 * status when it is done, and delete the tast according to the command.
 */

import duke.command.Command;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Loads the data from given file path.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns a string representation of duke's response to the DialogBox.
     * @param input String of user input.
     * @return String of duke's response to be shown.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            return ui.sayBye();
        } else {
            try {
                parser = new Parser(input);
                Command command = parser.parseCommand();
                return command.execute(tasks, storage);
            } catch (DukeException | IOException ex) {
                return ex.getMessage();
            }
        }
    }

    public String getGreeting() {
        return ui.greet();
    }
}