package duke;

import java.util.List;

public interface Ui {
    /**
     * Outputs a list of lines.
     *
     * @param messages List of lines.
     */
    void showMessage(List<String> messages);

    /**
     * Outputs a list of lines.
     *
     * @param messages List of lines.
     */
    default void showMessage(String... messages) {
        showMessage(List.of(messages));
    }

    /**
     * Shows an error message.
     *
     * @param message Error message.
     */
    default void showError(String message) {
        showMessage(message);
    }

    /**
     * Outputs a welcome message.
     */
    default void showWelcome() {
        showMessage("Hello! I'm Duke", "What can I do for you?");
    }
}
