package duke.exception;

import java.io.IOException;

public class DukeIllegalFileFormatException extends IOException {
    public DukeIllegalFileFormatException() {
        super("Please clear any previous Duke.txt in unsupported format!");
    }
}
