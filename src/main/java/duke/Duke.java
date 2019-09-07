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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        // Guard function for bye command
        if (input.equals("bye")) {
            // Save file
            try {
                this.storage.save(this.tasks);
            } catch (DukeException e) {
                return e.getMessage();
            }
            return "Bye. Hope to see you again soon!";
        }

        try {
            return parser.parse(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
