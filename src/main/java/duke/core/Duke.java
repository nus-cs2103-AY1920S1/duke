package duke.core;

import duke.exception.DukeException;
import duke.command.Command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.text.Parser;
import duke.ui.Ui;

public class Duke {
    protected TaskList tasks;
    protected Storage storage;
    protected Ui ui;
    protected String output;
    protected boolean isExit = false;

    public Duke() {
    }

    /**
     * duke.core.Duke Constructor that takes in a filePath where application data would be stored.
     *
     * @param filePath Path of storage file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } finally {
            ui.setTasks(tasks);
        }
    }

    public static void main(String[] args) {
        Duke newDuke = new Duke("data/duke.txt");
        newDuke.run();
    }

    /**
     * duke.core.Main run method. Application is in a constant loop until bye command changes isExit to true and
     * break out of the loop.
     */
    public void run() {
        ui.showWelcome();
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                System.out.println(output);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
            System.out.println(ui.getOutputStringAndClear());
        }
    }

    public boolean getExit() {
        return isExit;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            String fullCommand = input;
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return ui.getOutputStringAndClear();
    }
}
