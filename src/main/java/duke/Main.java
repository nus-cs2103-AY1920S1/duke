package duke;

import duke.command.Command;
import duke.task.TaskList;
import duke.textual.Parser;
import duke.ui.Ui;

/**
 * The main class.
 */
class Main {
    private final TaskList tl;
    private final Ui ui;
    private final Parser ps;

    /**
     * Initializes a new Duke session.
     */
    private Main() {
        ui = new Ui();
        tl = new TaskList();
        ps = new Parser();
    }

    /**
     * Starts up the initialized Duke session.
     */
    private void run() {
        boolean hasExited = false;
        ui.printWelcome();
        while (!hasExited) {
            try {
                Command c = ps.parseLine();
                c.execute(tl, ui);
                hasExited = c.isExit();
            } catch (IllegalArgumentException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
