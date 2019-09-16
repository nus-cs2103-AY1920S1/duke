package duke.storage;

import java.io.IOException;

public class FileIoException extends SerializerException {
    public FileIoException(String message, IOException exc) {
        super(message, exc);
    }
}
