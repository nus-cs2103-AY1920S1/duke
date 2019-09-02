/**
 * Main driving class for Duke.
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class Duke {

    private Storage storage;
    private TaskList list;
    private Ui ui;

    public Duke(String file) {
        ui = new Ui();
        storage = new Storage(file);
        list = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(list, ui, storage);
            isExit = c instanceof ExitCommand;
        }
    }


    public static void main(String[] args) {
        Duke duke = new Duke("Duke_List.txt");
        duke.run();
    }
}