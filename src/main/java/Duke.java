import command.Command;
import task.TaskList;
import textual.Parser;
import textual.Ui;

/**
 * The main class.
 */
class Duke {
    private final TaskList tl;
    private final Ui ui;
    private final Parser ps;

    /**
     * Initializes a new Duke session.
     */
    private Duke() {
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
        new Duke().run();
    }
}
