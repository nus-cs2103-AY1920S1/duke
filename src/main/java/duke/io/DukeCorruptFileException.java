package duke.io;

import duke.DukeException;

public class DukeCorruptFileException extends DukeException {
    public DukeCorruptFileException(String filePath) {
        super(new StringBuffer("The file at ").append(filePath).append(" is formatted incorrectly.").toString());
    }
}