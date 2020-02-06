package dose.util.gui;

import dose.Dose;
import dose.command.Command;
import dose.task.TaskList;
import dose.util.Parser;
import dose.util.Storage;
import dose.util.UiMessage;
import dose.util.exception.DoseException;

public class GuiDose implements Dose {
    Storage storage;
    TaskList tasks;
    String filePath = "/data/tasks.txt";

    /** GUI implementation of Dose uses a Gui object to represent its UI. */
    Gui ui;

    /**
     * Creates a new instance of Dose to be run from the GUI, with the default filePath.
     */
    public GuiDose() {
        ui = new Gui();
        storage = new Storage(filePath, ui);
        tasks = new TaskList();
    }

    /**
     * Get response from Dose to be displayed in the UI.
     * May not be required after UI implementation is refined.
     * @return Response from Dose to be displayed in the UI.
     */
    public void getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (DoseException e) {
            ui.showError(e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // dummy implementation
//        return "Dose heard: " + input;
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
