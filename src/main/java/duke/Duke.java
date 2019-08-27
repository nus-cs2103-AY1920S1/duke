package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Setups Duke.
     * @param args Setup arguments
     */
    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
            Ui.printIndented(String.format("Loaded from %s", storage.getFilePath()));
        } catch (DukeException ex) {
            Ui.printIndented(ex.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke.
     */
    private void run() {
        Ui.sayGreeting();
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.nextCommand();
            Ui.printDivider();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage);
                storage.save(tasks);
                isExit = c.isExit();
            } catch (DukeException ex) {
                Ui.printIndented(ex.getMessage());
            } finally {
                Ui.printDivider();
            }
        }
    }
}
