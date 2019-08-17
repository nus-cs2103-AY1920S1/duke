import command.Command;
import parser.Parser;
import task.TaskList;
import ui.Ui;

//@@author Parcly-Taxel
class Duke {
    private final TaskList tl;
    private final Ui ui;
    private final Parser ps;

    /**
     * Initialises a new Duke session.
     */
    private Duke() {
        ui = new Ui();
        tl = new TaskList();
        ps = new Parser();
    }

    /**
     * Starts up the initialised Duke session.
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
