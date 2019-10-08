package duke;

import error.storage.StorageException;
import storage.FileSystemStorage;
import storage.InMemStorage;
import storage.Storage;

import ui.Ui;
import ui.UiControllerFactory;
import ui.UiDriver;
import ui.UiType;

/**
 * A factory class to generate duke runtime options. The DukeOptions instance created will encapsulate all the
 * dependencies needed for the program to run.
 */
public class OptionsFactory {

    private static String getDefaultStorageFilePath() {
        return System.getProperty("user.home") + "/Duke/tasks.DAT";
    }

    /**
     * Generates options based on custom parameters.
     * @param isGuiEnabled to enable JavaFx gui
     * @param isPersistentDataEnabled to enable changes to be stored in local memory
     * @return DukeOptions object encompassing all the runtime configurations.
     */
    public static DukeOptions select(boolean isGuiEnabled, boolean isPersistentDataEnabled, UiDriver driver) {
        Ui ui;
        Storage storage;

        // Setup UiController
        if (isGuiEnabled) {
            ui = UiControllerFactory.createUiController(driver, UiType.JAVAFX);
        } else {
            ui = UiControllerFactory.createUiController(driver, UiType.CLI);
        }

        // Setup file storage
        if (isPersistentDataEnabled) {
            storage = OptionsFactory.getPersistentStorage();
        } else {
            storage = new InMemStorage();
        }

        return getOptions(ui, storage);
    }

    private static Storage getPersistentStorage() {
        try {
            Storage storage = FileSystemStorage.getInstance(getDefaultStorageFilePath());
            System.out.println("Storage file found.");
            return storage;
        } catch (StorageException e) {
            System.out.println("Unable to access storage file.");
            System.out.println("Starting the app without a persistent file storage.");
            return new InMemStorage();
        }
    }

    private static DukeOptions getOptions(Ui ui, Storage storage) {
        return new DukeOptions() {
            @Override
            public Ui getUiController() {
                return ui;
            }

            @Override
            public Storage getStorage() {
                return storage;
            }
        };
    }
}
