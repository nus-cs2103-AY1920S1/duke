package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    /**
     * Starts up the Duke program.
     * 
     * @param args options. (Currently none)
     */
    public static void main(String[] args) {
        ui = Ui.getInstance();
        storage = new Storage("data/duke.txt");
        tasks = storage.loadTasks();

        ui.printLine("Hello! I'm Duke\nWhat can I do for you?");

        boolean isRunning = true;
        while (isRunning) {
            String input = ui.getNextLine();

            try {
                Command c = Parser.parse(input);
                String output = c.execute(tasks, storage);
                ui.printLine(output);

                isRunning = !c.isExit();
            } catch (DukeException e) {
                ui.printLine(e.getMessage());
            }
        }
    }

    /**
     * TODO.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
