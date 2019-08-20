public class InvalidKeywordException extends IllegalArgumentException {
    public InvalidKeywordException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
