package util;

import duke.task.Task;

import java.util.List;

/**
 * Utility class that formats messages to be printed.
 */
public class OutputBuilder {
    private StringBuilder message;

    public OutputBuilder() {
        message = new StringBuilder();
    }

    /**
     * Constructor with message.
     * @param message string to be formatted
     */
    public OutputBuilder(String message) {
        this.message = new StringBuilder(message);
    }

    /**
     * Adds string to end of current message.
     * @param text string to be added.
     * @return modified message
     */
    public OutputBuilder append(String text) {
        message.append(text);
        return this;
    }

    /**
     * Adds int to end of current message.
     * @param i int to be added.
     * @return modified message
     */
    public OutputBuilder append(int i) {
        message.append(String.valueOf(i));
        return this;
    }

    /**
     * Adds another message to end of current message.
     * @param outputBuilder message to be added.
     * @return modified message.
     */
    public OutputBuilder append(OutputBuilder outputBuilder) {
        message.append(outputBuilder.build());
        return this;
    }

    /**
     * Adds new line to current message.
     * @return modified message.
     */
    public OutputBuilder newLine() {
        message.append("\n");
        return this;
    }

    /**
     * Adds indent to current message.
     * @return modified message.
     */
    public OutputBuilder indent() {
        message.append(" ");
        return this;
    }

    /**
     * Returns formatted message.
     * @return message.
     */
    public String build() {
        return message.toString();
    }

    /**
     * Appends task descriptions to the back of message.
     * @param tasks tasks to be appended.
     * @return modified message.
     */
    public OutputBuilder appendTasks(List<Task> tasks) {
        if (!tasks.isEmpty()) {
            message.append("1.")
                    .append(tasks.get(0).getTaskDescription());
        }

        for (int i = 1; i < tasks.size(); i++) {
            message.append("\n")
                    .append(i + 1)
                    .append(".")
                    .append(tasks.get(i).getTaskDescription());
        }

        return this;
    }
}
