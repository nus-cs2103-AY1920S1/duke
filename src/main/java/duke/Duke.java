import Commands.Command;
import DirectProcessor.Parser;
import DirectProcessor.Storage;
import DirectProcessor.TaskList;
import DirectProcessor.Ui;

import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.reload());
        } catch(IOException e) {
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
            } catch (Exception e) {
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
