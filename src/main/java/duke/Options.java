package duke;

import duke.task.TasksController;
import storage.Storage;
import ui.input.InputHandler;
import ui.output.OutputHandler;

public interface Options {
    public InputHandler getInput();
    public OutputHandler getOutput();
    public Storage getStorage();
}
