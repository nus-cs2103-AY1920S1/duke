package util;

import duke.task.Task;

import java.util.List;

/***
 * <p>
 * Utility class that encapsulates message strings to be printed by the program.
 * DukeOutput uses DukeMessage to auto-format output messages.
 * </p>
 */
public class OutputBuilder {
    private StringBuilder message;

    /***
     * <p>
     * DukeMessage default constructor.
     * </p>
     */
    public OutputBuilder() {
        message = new StringBuilder();
    }

    /***
     * <p>
     * DukeMessage constructor.
     * </p>
     * @param message string to be encapsulated.
     */
    public OutputBuilder(String message) {
        this.message = new StringBuilder(message);
    }

    /***
     * <p>
     * Adds string to end of current message.
     * </p>
     * @param text string to be added.
     * @return modified DukeMessage.
     */
    public OutputBuilder append(String text) {
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
    public OutputBuilder append(int i) {
        message.append(String.valueOf(i));
        return this;
    }

    /***
     * <p>
     * Adds DukeMessage to end of current message.
     * </p>
     * @param outputBuilder DukeMessage to be added.
     * @return modified DukeMessage.
     */
    public OutputBuilder append(OutputBuilder outputBuilder) {
        message.append(outputBuilder.build());
        return this;
    }

    /***
     * <p>
     * Adds new line to current message.
     * </p>
     * @return modified DukeMessage.
     */
    public OutputBuilder newLine() {
        message.append("\n");
        return this;
    }

    /***
     * <p>
     * Adds indent to current message.
     * </p>
     * @return modified DukeMessage.
     */
    public OutputBuilder indent() {
        message.append(" ");
        return this;
    }

    /***
     * <p>
     * Returns encapsulated message.
     * </p>
     * @return message.
     */
    public String build() {
        return message.toString();
    }


    public OutputBuilder appendTasks(List<Task> tasks) {
        if (!tasks.isEmpty()) {
            message.append("1.")
                    .append(tasks.get(0).getDescription());
        }

        for (int i = 1; i < tasks.size(); i++) {
            message.append("\n")
                    .append(i + 1)
                    .append(".")
                    .append(tasks.get(i).getDescription());
        }

        return this;
    }
}
