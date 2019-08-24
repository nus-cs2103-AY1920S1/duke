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
    public void print() {
    }

    @Override
    public void print(String message) {
    }
}
