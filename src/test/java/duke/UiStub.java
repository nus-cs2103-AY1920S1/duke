package duke;

import duke.ui.Ui;

class UiStub extends Ui {

    @Override
    public void printMessage(String msg) {
        System.out.println("ok got it");
    }
}
