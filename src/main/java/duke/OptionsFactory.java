package duke;

import duke.task.TasksController;
import duke.task.tasks.Task;
import storage.Storage;
import ui.UiController;
import ui.cli.ClInput;
import ui.cli.ClOutput;
import ui.input.InputHandler;
import ui.output.OutputHandler;

import java.util.ArrayList;
import java.util.List;

public class OptionsFactory {
    public static Options select(boolean guiEnabled, boolean persistentDataEnabled) {
        InputHandler input = ClInput.getInstance();
        OutputHandler output = ClOutput.getInstance();

        return new Options() {
            @Override
            public InputHandler getInput() {
                return input;
            }

            @Override
            public OutputHandler getOutput() {
                return output;
            }

            @Override
            public Storage getStorage() {
                return null;
            }
        };
    }
}
