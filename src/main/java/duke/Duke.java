package duke;

import duke.Commands.Command;
import duke.DirectProcessor.Parser;
import duke.DirectProcessor.Storage;
import duke.DirectProcessor.TaskList;
import duke.DirectProcessor.Ui;

import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String fileName) {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.reload(fileName));
        } catch(IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch(DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.takeCommand();
                ui.drawLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.drawLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("taskfile.txt").run();
    }
}
