package duke.util.gui;

import duke.Duke;

public class GuiDuke extends Duke {

    /**
     * Creates a new instance of Duke to be run from the GUI, with the default filePath.
     */
    public GuiDuke() {
//        ui = new Gui();
//        storage = new Storage(filePath);
//        tasks = new TaskList();
    }

    public String getResponse(String input) {
//        try {
//            Command c = Parser.parse(input);
//            c.execute(tasks, ui);
//        } catch (DukeException e) {
//            ui.showError(e);
//        }

        // dummy implementation
        return "Duke heard: " + input;
        //return ui.getResponse();
    }

}
