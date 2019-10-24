package duke;

import java.io.FileNotFoundException;

/**
 * The Duke program implements an application that
 * acts like a task scheduler for the user.
 *
 * @author Calvin
 * @version 1.0
 * @since 2019-08-20
 */

public class Duke {

    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;

    public Duke() {
    }
    /**
     * This constructor takes in the filePath and initiates the necessary
     * classes required.
     *
     * @param filePath the local directory of the file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
            storage.createNew();
        }
    }
    public static void main(String[] args) {
    }

    /**
     * Parses user input and executes the respective commands.
     *
     * @param input
     * @return duke's response after parsing user command
     */
    String getResponse(String input) {
        if (input.equals("bye")) {
            System.exit(0);
            return null;
        } else {
            return Parser.parse(input).execute(tasks, ui, storage);
        }
    }
}

