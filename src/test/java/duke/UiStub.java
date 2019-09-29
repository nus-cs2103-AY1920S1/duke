package duke;

import duke.ui.Ui;

public class UiStub extends Ui {
    UiStub() {
        super();
    }

    @Override
    public String readCommand() {
        return null;
    }

    @Override
    public void printLine(String message) {
    }

    @Override
    public void printToConsole() {
    }

    @Override
    public void printToConsole(String message) {
    }
}
