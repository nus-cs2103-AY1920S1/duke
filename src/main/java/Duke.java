package duke;

import duke.command.Command;
import duke.error.DukeException;

/**
 * main class.
 * */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * constructor of main class.
     * initialize ui, storage, tasklist objects
     *
     * @param filePath String of file location
     * */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * main program for execution.
     * */
    public void run() {
        ui.showWelcome();
        Parser parser = new Parser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
