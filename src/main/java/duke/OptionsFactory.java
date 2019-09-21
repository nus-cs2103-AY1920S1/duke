package duke;

import error.storage.StorageException;
import storage.FileSystemStorage;
import storage.Storage;

import ui.UiController;
import ui.UiControllerFactory;
import ui.UiDriver;
import ui.UiType;

/**
 * Factory to generate duke runtime options.
 */
public class OptionsFactory {

    private static String getDefaultStorageFilePath() {
        return System.getProperty("user.home") + "/bin/duke.txt";
    }

    /**
     * Generates options based on custom parameters.
     * @param isGuiEnabled to enable JavaFx gui
     * @param isPersistentDataEnabled to enable changes to be stored in local memory
     * @return
     */
    public static DukeOptions select(boolean isGuiEnabled, boolean isPersistentDataEnabled, UiDriver driver) {
        UiController uiController;
        Storage storage;

        // Setup UiController
        if (isGuiEnabled) {
            uiController = UiControllerFactory.createUiController(driver, UiType.JAVAFX);
        } else {
            uiController = UiControllerFactory.createUiController(driver, UiType.CLI);
        }

        // Setup file storage
        if (isPersistentDataEnabled) {
            storage = OptionsFactory.getPersistentStorage();
        } else {
            storage = null;
        }

        return getOptions(uiController, storage);
    }

    private static Storage getPersistentStorage() {
        try {
            Storage storage = FileSystemStorage.getInstance(getDefaultStorageFilePath());
            System.out.println("Storage file found.");
            return storage;
        } catch (StorageException e) {
            System.out.println("Unable to access storage file.");
            return null;
        }
    }

    private static DukeOptions getOptions(UiController uiController, Storage storage) {
        return new DukeOptions() {
            @Override
            public UiController getUiController() {
                return uiController;
            }

            @Override
            public Storage getStorage() {
                return storage;
            }
        };
    }
}
