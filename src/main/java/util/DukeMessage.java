package util;

/***
 * Utility class that encapsulates message strings to be printed by the program.
 * DukeOutput uses DukeMessage to auto-format output messages.
 */
public class DukeMessage {
    private StringBuilder message;

    /***
     * DukeMessage default constructor.
     */
    public DukeMessage() {
        message = new StringBuilder();
    }

    /***
     * DukeMessage constructor.
     * @param message string to be encapsulated.
     */
    public DukeMessage(String message) {
        this.message = new StringBuilder(message);
    }

    /***
     * Adds string to end of current message.
     * @param text string to be added.
     * @return modified DukeMessage.
     */
    public DukeMessage append(String text) {
        message.append(text);
        return this;
    }

    /***
     * Adds int to end of current message.
     * @param i int to be added.
     * @return modified DukeMessage.
     */
    public DukeMessage append(int i) {
        message.append(String.valueOf(i));
        return this;
    }

    /***
     * Adds DukeMessage to end of current message.
     * @param dukeMessage DukeMessage to be added.
     * @return modified DukeMessage.
     */
    public DukeMessage append(DukeMessage dukeMessage) {
        message.append(dukeMessage.getMessage());
        return this;
    }

    /***
     * Adds new line to current message.
     * @return modified DukeMessage.
     */
    public DukeMessage newLine() {
        message.append("\n");
        return this;
    }

    /***
     * Adds indent to current message.
     * @return modified DukeMessage.
     */
    public DukeMessage indent() {
        message.append(" ");
        return this;
    }

    /***
     * Returns encapsulated message
     * @return message.
     */
    String getMessage() {
        return message.toString();
    }
}
