package duke.command;

import duke.task.TasksController;
import error.command.CommandCreationException;
import error.ui.UiException;
import ui.UiController;

public abstract class Command {
    private CommandType type;
    protected UiController ui;
    protected TasksController tasksController;

    protected Command(CommandType type) throws CommandCreationException {
        this.type = type;
    }

    public void setUi(UiController ui) {
        this.ui = ui;
    }

    public void setTasksController(TasksController controller) {
        this.tasksController = controller;
    }

    public abstract void execute() throws UiException;
}
