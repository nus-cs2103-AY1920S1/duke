package duke.command.factory;

import duke.command.command.AddCommand;
import duke.task.TasksController;
import duke.task.Task;
import error.task.UnknownTaskException;
import error.task.TaskCreationException;
import error.ui.UiException;
import ui.UiController;

import java.util.Optional;

public class AddCommandFactory {
    private TasksController tasksController;
    private UiController ui;

    private static final String UNKNOWN_TASK_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    public AddCommandFactory(TasksController tasksController, UiController ui) {
        this.tasksController = tasksController;
        this.ui = ui;
    }

    public Optional<AddCommand> parse(String input) throws UiException {
        try {

            Task task = getTask(input);
            AddCommand command = new AddCommand(task, tasksController);
            return Optional.of(command);

        } catch (UnknownTaskException e) {

            ui.displayOutput(UNKNOWN_TASK_MESSAGE);
        } catch (TaskCreationException e) {

            ui.displayOutput(e.toString());
        }

        return Optional.empty();
    }

    private Task getTask(String input) throws UnknownTaskException, TaskCreationException {
        return null;
    }
}
