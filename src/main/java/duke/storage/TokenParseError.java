package duke.storage;

public abstract class TokenParseError extends SerializerException {
    public TokenParseError(String message) {
        super(message);
    }
}
