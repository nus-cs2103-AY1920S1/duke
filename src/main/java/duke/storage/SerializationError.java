package duke.storage;

abstract class SerializationError extends SerializerException {
    public SerializationError(String message) {
        super(message);
    }
}
