package duke;

import storage.Storage;
import ui.input.InputHandler;
import ui.output.OutputHandler;

/**
 * Interface for duke runtime options.
 */
public interface Options {
    /**
     * Provides input channel for dealing with user input.
     * @return input channel
     */
    public InputHandler getInput();

    /**
     * Provides output channel for printing program output.
     * @return output channel
     */
    public OutputHandler getOutput();

    /**
     * Returns storage instance that handles reading and writing of data.
     * @return storage
     */
    public Storage getStorage();
}
