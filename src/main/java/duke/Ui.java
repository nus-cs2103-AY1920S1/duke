package duke;

import java.util.List;

public interface Ui {
    /**
     * Outputs a list of lines.
     *
     * @param message List of lines.
     */
    void showMessage(List<String> message);

    /**
     * Outputs a list of lines.
     *
     * @param message List of lines.
     */
    default void showMessage(String... message) {
        showMessage(List.of(message));
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
     * Shows a welcome message.
     */
    default void showWelcome() {
        showMessage("Hello! I'm Duke", "What can I do for you?");
    }
}
