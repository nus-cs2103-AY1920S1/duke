package duke.util.gui;

import duke.Duke;
import duke.command.Command;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.UiMessage;
import duke.util.exception.DukeException;

public class GuiDuke implements Duke {
    Storage storage;
    TaskList tasks;
    String filePath = "data/tasks.txt";

    /** GUI implementation of Duke uses a Gui object to represent its UI. */
    Gui ui;

    /**
     * Creates a new instance of Duke to be run from the GUI, with the default filePath.
     */
    public GuiDuke() {
        ui = new Gui();
        storage = new Storage(filePath, ui);
        tasks = new TaskList();
    }

    /**
     * Get response from Duke to be displayed in the UI.
     * May not be required after UI implementation is refined.
     * @return Response from Duke to be displayed in the UI.
     */
    public void getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showError(e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // dummy implementation
//        return "Duke heard: " + input;
        //return ui.getResponse();
    }

    @Override
    public void run() {
        // todo: figure out why welcome message doesn't appear until user's first input
        ui.showMessage(UiMessage.WELCOME);
        initializeStorage();
    }

    /**
     * Attempts to import an existing task list.
     */
    public void initializeStorage() {
        try {
            TaskList tasksFromFile = storage.load();
            tasks = tasksFromFile;
        } catch (Exception e) {
            // temporary haxx
            e.printStackTrace();
        }
    }
}
