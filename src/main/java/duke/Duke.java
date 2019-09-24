package duke;

import java.io.File;

/**
 * This program is an interactive task list that takes in several
 * preset commands from the user to create tasks, view the list of
 * tasks and mark each of it as completed. This class contains the
 * main method and is responsible for all input/output and Task creation.
 * @author Gabriel Ong
 *
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Returns a instance of duke.
     */
    public Duke() {
        storage = new Storage("." + File.separator + "data");
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            // Create new TaskList if one is non-existent
            tasks = new TaskList();
        }
        parser = new Parser(tasks);
    }

    /**
     * Passes a user-inputted string to the parser and returns the parser's output.
     * If the response is a "bye" command, it saves the current task list.
     * @param input String input from the user
     * @return returns a String from Duke back to the UI
     */
    protected String getResponse(String input) {
        // Guard function for bye command
        if (input.equals("bye")) {
            // Save file
            try {
                assert this.storage != null : "Storage not initialized.";
                this.storage.save(this.tasks);
            } catch (DukeException e) {
                return e.getMessage();
            }
            return "Bye. Hope to see you again soon!";
        }

        try {
            assert this.parser != null : "Parser not initialized.";
            return parser.parse(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
