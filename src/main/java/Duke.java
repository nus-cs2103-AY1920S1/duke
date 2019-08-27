import duke.command.Storage;
import duke.command.Ui;
import duke.tasklist.TaskList;

import java.io.*;

/**
 * Main class of DukeBot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     *
     * @param filePath relative file path to the designated file of storing list of tasks.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui(storage);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.initMessage();
        while(true){
            try{
                ui.readUserInput();
            } catch(IllegalStateException e) {
                break;
            }
        }

    }

    public static void main(String[] args) {
        new Duke("/data/duke.txt").run();
    }
}
