package duke;

import command.Command;
import exception.DukeException;

import java.io.*;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates an instance of Duke, a task manager.
     * @param filePath a string storing the location of the text file which stores the tasks' data.
     * @throws IOException
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Initializes the program which users can input commands to interact with Duke.
     * @throws IOException
     */
    public void run() throws IOException {
        ui.showWelcome(tasks);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.print(e.getMessage());
            } finally {

            }
        }
        ui.showLine();
    }
}