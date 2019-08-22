package util;

public class DukeMessage {
    private StringBuilder message;

    public DukeMessage() {
        message = new StringBuilder();
    }

    public DukeMessage(String message) {
        this.message = new StringBuilder(message);
    }

    public DukeMessage append(String text) {
        message.append(text);
        return this;
    }

    public DukeMessage append(int i) {
        message.append(String.valueOf(i));
        return this;
    }

    public DukeMessage append(DukeMessage dukeMessage) {
        message.append(dukeMessage.getMessage());
        return this;
    }

    public DukeMessage newLine() {
        message.append("\n");
        return this;
    }

    public DukeMessage indent() {
        message.append(" ");
        return this;
    }

    String getMessage() {
        return message.toString();
    }
}
