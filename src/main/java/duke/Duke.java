package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents a personal assistant chatbot that keeps track of tasks.
 * An <code>Duke</code> object corresponds to a new chatbot with its own <code>Storage</code> and <code>TaskList</code>.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for <code>Duke</code>.
     * @param filePath File path of file to store the information in the task list.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.drawLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printException(e);
            } finally {
                ui.drawLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("C:\\Users\\Yi Yin\\Documents\\Year 2\\Semester 1\\CS2103\\duke\\data\\duke.txt").run();
    }
}
