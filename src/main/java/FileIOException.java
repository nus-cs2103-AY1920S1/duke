import java.io.IOException;

class FileIOException extends SerializerException {
    public FileIOException(String message, IOException exc) {
        super(message, exc);
    }
}
