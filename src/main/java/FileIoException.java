import java.io.IOException;

class FileIoException extends SerializerException {
    public FileIoException(String message, IOException exc) {
        super(message, exc);
    }
}
