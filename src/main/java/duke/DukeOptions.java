package duke;

import storage.Storage;
import ui.Ui;

/**
 * An interface to encapsulate potential runtime configurations for the Duke program.
 */
public interface DukeOptions {
    /**
     * Returns a UiController that handles user input and displaying of the program output.
     * @return a UiController instance to handle I/O.
     */
    public Ui getUiController();

    /**
     * Returns a Storage instance that handles reading and writing of data.
     * @return the Storage instance to be used for reading and writing data.
     */
    public Storage getStorage();
}
