package duke;

import duke.task.TasksController;
import ui.UiController;
import ui.cli.ClInput;
import ui.cli.ClOutput;
import ui.input.InputHandler;
import ui.output.OutputHandler;

public interface Options {
    public UiController getUi();
    public TasksController getTasks();
}
