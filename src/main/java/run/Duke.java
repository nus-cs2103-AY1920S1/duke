package run;

import parser.Parser;
import exception.*;
import command.Command;

/**
 * Creates a new Duke interface task management system that has includes a task list, storage system and user
 * interface
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke
     * @param filePath to current save state file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs introductory messages and begins awaiting user input
     */
    public void run() {
        ui.introduction();
        ui.init();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("---------")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * main program in Java
     * @param args void
     */
    public static void main(String[] args) {
        new Duke("C:\\Users\\nisga\\OneDrive\\Desktop\\duke\\src\\main\\java\\data\\tasks.txt").run();
    }
}
