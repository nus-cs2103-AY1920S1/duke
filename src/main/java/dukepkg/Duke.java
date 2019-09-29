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

    /**
     * Instantiates a new Duke.
     */
    public Duke() {
        Storage storage = new Storage("data/zjtasks.txt");
        ui = new Ui();
        tasklist = new TaskList(storage, ui);
        tasklist.loadTaskHistory();
        parser = new Parser();

    }

    private void run() {
        ui.showGreeting();
        boolean isExit = false;
        while(true){
            if(isExit) {
                System.out.println("Existing programme...");
                System.exit(0);
            }
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

    /**
     * Gets response.
     *
     * @param input the input
     * @return the response
     */
    public String getResponse(String input) {
        try {
            Command c = parser.constructCommand(input);
            String uiMsg = c.execute(tasklist, ui);
            isExit = c.isExit();
            return uiMsg;
        } catch (DukeException e) {
            return ui.showDukeError(e);
        }
    }

    /**
     * Flag to indicate whether the system should exit.
     *
     * @return the boolean to indicate the system should exit.
     */
    public boolean isExit() {
      return this.isExit;
    }

}
