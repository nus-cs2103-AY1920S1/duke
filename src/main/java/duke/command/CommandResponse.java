package duke.command;

/**
 * Helper class to store the String response and boolean flag indicating program exit.
 */
public class CommandResponse {
    private String response;
    private boolean isExit;

    public CommandResponse(String response, boolean isExit) {
        this.response = response;
        this.isExit = isExit;
    }

    public String getResponse() {
        return this.response;
    }

    public boolean getIsExit() {
        return this.isExit;
    }
}
