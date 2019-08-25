package duke.io;

import duke.DukeException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class Input {
    private BufferedReader bufferedReader;

    public Input(InputStream inputStream) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public String get() throws DukeException {
        try {
            String userInput = bufferedReader.readLine();
            return userInput;
        } catch (IOException ex) {
            throw new DukeException("Input error");
        }
    }
}