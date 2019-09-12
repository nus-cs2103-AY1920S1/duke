
/**
 * This program is Duke, which stores task for users.
 * 
 * @author Timothy Yu Zhiwen
 */
public class Duke {
    private DukeManager dukeManager;

    public Duke() throws DukeException {
        dukeManager = new DukeManager();
    }

    /**
     * Returns the output which is obtained from running the user's input through Duke.
     * @param input The users input.
     * @return The String which is obtained when calling DukeException.
     */
    public String getResponse(String input) {
        assert !input.equals("") : "Empty input";
        String output;
        try {
            output = dukeManager.runDuke(input);
        } catch (DukeException e) {
            output = e.getMessage();
        }
        return output;
    }

    public DukeManager getDukeManager() {
        return this.dukeManager;
    }
    
}
