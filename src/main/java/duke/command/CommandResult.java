package duke.command;

/** Represents the result of executing a command. */
public class CommandResult {
    /** Represents whether user wants to exit the program. */
    private boolean isExit = false;

    /** Feedback to be printed to user. */
    private String feedBack;

    /** Constructs a CommandResult instance. */
    public CommandResult(String feedBack) {
        this.feedBack = feedBack;
    }

    /**
     * Returns whether the user wants to exit the program.
     * @return whether user wants to exit the program.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Sets isExit to true.
     */
    public void exit() {
        isExit = true;
    }

    /**
     * Returns the feedback to be printed.
     * @return feedback for user.
     */
    public String getFeedBack() {
        return feedBack;
    }
}
