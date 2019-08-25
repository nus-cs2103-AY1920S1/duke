import duke.command.Command;
import duke.command.Parser;
import duke.exception.*;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.CmdUx;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Drives the Duke bot
 * This is the main driver class and entry point.
 */
public class Duke {

    private TaskList tasks;
    private Ui ui;


    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
    }

    public void run() {

        this.ui.printWelcomeMsg();

        boolean isGoodbye = false;

        while (!isGoodbye) {
            try {
                String fullInput = ui.readInput();
                Command c = Parser.parse(fullInput);
                c.execute(tasks, ui);
                isGoodbye = c.isExit();
            } catch (DukeException de) {
                ui.exposeError(de.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke dukeInstance = new Duke();
        dukeInstance.run();
    }

}
