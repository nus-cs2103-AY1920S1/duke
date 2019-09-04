package duke;

import duke.tasks.TaskList;
import duke.commands.Command;

public class Duke {
    private Parser parser;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke() {
        parser = new Parser();
        storage = new Storage();
        ui = new Ui();
        try {
            taskList = storage.loadData();
        } catch (DukeException e) {
            ui.showException(e);
            ui.print();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.getInput();
                Command command = parser.parse(input);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showException(e);
            } finally {
                ui.print();
            }
        }
    }

    /**
     * Main method of duke project.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}

