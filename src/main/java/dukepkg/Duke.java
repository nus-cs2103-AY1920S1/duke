package dukepkg;

import dukepkg.commands.Command;
import dukepkg.exceptions.DukeException;

public class Duke {
    private static Storage storage;
    private static Ui ui;
    private static TaskList tasklist;
    private static Parser parser;

    private Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        tasklist = new TaskList(storage, ui);
        tasklist.loadTaskHistory();
        parser = new Parser();
    }

    private void run() {
        ui.showGreeting();
        boolean isExit = false;
        while(!isExit){
            try {
                String command = ui.readCommand();
                Command c = parser.constructCommand(command);
                c.execute(tasklist, ui, storage);
                isExit = c.isExit();
            } catch(DukeException e) {
                ui.showDukeError(e);
            }
        }
    }
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
