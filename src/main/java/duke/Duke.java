package duke;

import java.io.FileNotFoundException;

/**
 * The Duke program implements an application that
 * acts like a digital notebook for the user.
 *
 * @author Calvin
 * @version 1.0
 * @since 2019-08-20
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
        }
    }

    public static void main(String[] args) {
    }

    String getResponse(String input) {
        if(input.equals("bye")) {
            System.exit(0);
            return null;
        } else {
            Duke duke = new Duke("C:\\duke\\src\\main\\java\\data\\duke.txt");
            return Parser.parse(input).execute(duke.tasks, duke.ui, duke.storage);

        }

    }
}

