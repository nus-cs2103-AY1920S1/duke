package duke;

import error.storage.StorageException;
import storage.FileSystemStorage;
import storage.Storage;

import ui.cli.ClInput;
import ui.cli.ClOutput;
import ui.fx.FxMain;
import ui.input.InputHandler;
import ui.output.OutputHandler;

public class OptionsFactory {

    private static String getDefaultStorageFilePath() {
        return System.getProperty("user.home") + "/bin/duke.txt";
    }

    public static Options select(boolean guiEnabled, boolean persistentDataEnabled) {
        InputHandler input = null;
        OutputHandler output = null;

        if (guiEnabled) {
            FxMain gui = FxMain.getInstance();
            input = gui;
            output = gui;
        } else {
            input = ClInput.getInstance();
            output = ClOutput.getInstance();
        }

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
