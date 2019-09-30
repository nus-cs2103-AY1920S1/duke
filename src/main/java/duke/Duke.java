package duke;

import duke.util.UiMessage;
import duke.command.Command;
import duke.util.exception.DukeException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String filePath = "data/tasks.txt";

    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();

        ui.showMessage(UiMessage.WELCOME);

        try {
            TaskList tasksFromFile = storage.load();
            tasks = tasksFromFile;
        } catch (Exception e) {
            // temporary haxx
            e.printStackTrace();
        }
    }

    private void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                // todo: remove redundant showLine() for bye command
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
        storage.save(tasks);
        ui.showMessage(UiMessage.GOODBYE);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui);
            if (c.isExit()) {
                System.exit(0);
            }
        } catch (DukeException e) {
            ui.showError(e);
        }

        // dummy implementation
        return "Duke heard: " + input;
        //return ui.getResponse();
    }
}
