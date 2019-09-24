package duke.command;

public class CommandResult {
    private String response;
    private boolean isExit;

    public CommandResult(String response, boolean isExit) {
        this.response = response;
        this.isExit = isExit;
    }

    public String getResponse() {
        return response;
    }

    public boolean isExit() {
        return isExit;
    }
}
