package duke.command.entities;

import duke.command.Command;
import duke.task.TasksController;
import error.command.CommandCreationException;
import ui.UiController;

/**
 * Returns a command instance.
 */
@FunctionalInterface
public interface CommandProducer {
    public Command getCommand(String arguments, UiController ui, TasksController tasksController) throws CommandCreationException;
}
