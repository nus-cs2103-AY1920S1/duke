package duke.ui;

import duke.core.DukeController;

public class GuiPrinter extends Printer {

    private DukeController controller;

    public GuiPrinter(DukeController controller) {
        this.controller = controller;
    }

    @Override
    public void printDuke(String text) {
        this.controller.addDukeDialog(text);
    }

    @Override
    public void printUser(String text) {
        this.controller.addUserDialog(text);
    }

}
