package seedu.duke.commands;

public class CommandResult {
    private final String feedback;

    CommandResult(String feedback) {
        this.feedback = feedback;
    }

    public String getFeedback() {
        return feedback;
    }
}
