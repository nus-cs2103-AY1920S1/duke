package util;

/***
 * <p>
 * Utility class that encapsulates message strings to be printed by the program.
 * DukeOutput uses DukeMessage to auto-format output messages.
 * </p>
 */
public class DukeMessage {
    private StringBuilder message;

    /***
     * <p>
     * DukeMessage default constructor.
     * </p>
     */
    public DukeMessage() {
        message = new StringBuilder();
    }

    /***
     * <p>
     * DukeMessage constructor.
     * </p>
     * @param message string to be encapsulated.
     */
    public DukeMessage(String message) {
        this.message = new StringBuilder(message);
    }

    /***
     * <p>
     * Adds string to end of current message.
     * </p>
     * @param text string to be added.
     * @return modified DukeMessage.
     */
    public DukeMessage append(String text) {
        message.append(text);
        return this;
    }

    /***
     * <p>
     * Adds int to end of current message.
     * </p>
     * @param i int to be added.
     * @return modified DukeMessage.
     */
    public DukeMessage append(int i) {
        message.append(String.valueOf(i));
        return this;
    }

    /***
     * <p>
     * Adds DukeMessage to end of current message.
     * </p>
     * @param dukeMessage DukeMessage to be added.
     * @return modified DukeMessage.
     */
    public DukeMessage append(DukeMessage dukeMessage) {
        message.append(dukeMessage.getMessage());
        return this;
    }

    /***
     * <p>
     * Adds new line to current message.
     * </p>
     * @return modified DukeMessage.
     */
    public DukeMessage newLine() {
        message.append("\n");
        return this;
    }

    /***
     * <p>
     * Adds indent to current message.
     * </p>
     * @return modified DukeMessage.
     */
    public DukeMessage indent() {
        message.append(" ");
        return this;
    }

    /***
     * <p>
     * Returns encapsulated message.
     * </p>
     * @return message.
     */
    String getMessage() {
        return message.toString();
    }
}
