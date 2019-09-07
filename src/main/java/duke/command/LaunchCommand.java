package duke.command;

import duke.main.Main;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UserInterface;
import javafx.application.Application;

public class LaunchCommand implements Command {

    String[] args;

    public LaunchCommand(String[] args) {
        this.args = args;
    }

    public String execute(TaskList tasks, UserInterface commandLineUserInterface, Storage storage) {
        Application.launch(Main.class, args);
        return null;
    }

    public boolean isExit() {
        return false;
    }
}
