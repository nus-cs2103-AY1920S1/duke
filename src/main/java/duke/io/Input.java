package duke.io;

import duke.DukeException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Handler for user input
 */
public class Input {
    private BufferedReader bufferedReader;

    /**
     * Constructs the user input handler
     *
     * @param inputStream The input stream where the which needs to be read from
     */
    public Input(InputStream inputStream) {
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    /**
     * Returns the next line in the buffer where the user input is stored into
     *
     * @return The string representation of the next line in the buffer
     * @throws DukeException Exception to be thrown when the an error occurs when trying to read from the buffer
     */
    public String get() throws DukeException {
        try {
            return bufferedReader.readLine();
        } catch (IOException ex) {
            throw new DukeException("Input error");
        }
    }
}