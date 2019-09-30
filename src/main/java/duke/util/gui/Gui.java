package duke.util.gui;

import duke.task.TaskList;
import duke.util.Ui;
import duke.util.UiMessage;
import duke.util.exception.DukeException;

public class Gui implements Ui {

    @Override
    public String readCommand() {
        return null;
    }

    @Override
    public void showMessage(UiMessage uiMessage) {

    }

    @Override
    public void showError(DukeException exception) {

    }

    @Override
    public void showTasks(TaskList tasks) {

    }

//    public void setColourScheme(ColourScheme colourScheme) {
//        MainWindow.setColourScheme(colourScheme);
//    }
}
