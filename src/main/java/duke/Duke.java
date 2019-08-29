package duke;

import java.io.File;

/**
 * Duke.java
 * CS2103T
 * @author Gabriel Ong
 *
 * This program is an interactive task list that takes in several preset commands from the user
 * to create tasks, view the list of tasks and mark each of it as completed.
 * This class contains the main method and is responsible for all input/output and Task creation.
 *
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns a instance of duke.
     * @param filePath a filepath indicating the storage directory for duke
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs duke to start taking in input from user.
     */
    public void run() {
        ui.greetHello();

        this.tasks = ui.runInputLoop(this.tasks);

        //Save file before exiting
        try {
            this.storage.save(this.tasks);
        } catch (DukeException e) {
            this.ui.showError(e);
        }

        this.ui.greetBye();
    }

    public static void main(String[] args) {
        new Duke("." + File.separator + "data").run();
    }

}
