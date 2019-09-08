package dukepkg;

import dukepkg.commands.Command;
import dukepkg.exceptions.DukeException;


import java.lang.*;

/**
 * Duke, the task bot class.
 */
public class Duke {

    private static Ui ui;
    private static TaskList tasklist;
    private static Parser parser;
    private boolean isExit;

    public Duke() {
        Storage storage = new Storage("data/tasks.txt");
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
                c.execute(tasklist, ui);
                isExit = c.isExit();
            } catch(DukeException e) {
                ui.showDukeError(e);
            }
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    public String getResponse(String input) {
        try {
            Command c = parser.constructCommand(input);
            isExit = c.isExit();
            return c.execute(tasklist, ui);

        } catch (DukeException e) {
            return ui.showDukeError(e);
        }
    }

    public boolean isExit() {
      return this.isExit;
    }

}
