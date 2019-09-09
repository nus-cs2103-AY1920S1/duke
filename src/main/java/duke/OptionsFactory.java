package duke;

import duke.task.TasksController;
import duke.task.tasks.Task;
import error.ConfigurationException;
import error.StorageException;
import storage.FileSystemStorage;
import storage.Storage;
import ui.UiController;
import ui.cli.ClInput;
import ui.cli.ClOutput;
import ui.input.InputHandler;
import ui.output.OutputHandler;

import java.util.ArrayList;
import java.util.List;

public class OptionsFactory {

    private static String getDefaultStorageFilePath() {
        return System.getProperty("user.home") + "/bin/duke.txt";
    }

    public static Options select(boolean guiEnabled, boolean persistentDataEnabled) {
        InputHandler input = ClInput.getInstance();
        OutputHandler output = ClOutput.getInstance();

        Storage storage = null;

        // Setup file storage
        if (persistentDataEnabled) {
            try {
                storage = FileSystemStorage.getInstance(getDefaultStorageFilePath());
                System.out.println("Storage file found.");
            } catch (StorageException e) {
                System.out.println("Unable to access storage file.");
            }
        }

        return getOptions(input, output, storage);
    }

    private static Options getOptions(InputHandler input, OutputHandler output, Storage storage) {
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
                return storage;
            }
        };
    }
}
