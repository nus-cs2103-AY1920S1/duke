package duke.exception;

import java.io.FileNotFoundException;

public class DukeFileNotFoundException extends FileNotFoundException {
    public DukeFileNotFoundException() {
        super("file not found");
    }
}
