package duke.command;

/**
 * CommandResult class which stores the results of each command.
 */
public class CommandResult {

    private String userFeedback;
    private boolean isExit;

    /**
     * Initialises a CommandResult.
     * @param userFeedback Feedback that will be given to the user.
     * @param isExit Boolean that states whether the result is an exit command.
     */
    public CommandResult(String userFeedback, boolean isExit) {
        this.userFeedback = userFeedback;
        this.isExit = isExit;
    }

    /**
     * Convenience initializer if result is not an exit command.
     * @param userFeedback Feedback provided to the user.
     */
    public CommandResult(String userFeedback) {
        this.userFeedback = userFeedback;
        this.isExit = false;
    }

    /**
     * Gets the user feedback from the result.
     * @return User feedback.
     */
    public String getUserFeedback() {
        return this.userFeedback;
    }

    /**
     * Boolean to show whether result is an exit command.
     * @return True if result is an exit command, false otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
