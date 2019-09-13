
/**
 * This program is Rori, which stores task for users.
 * 
 * @author Timothy Yu Zhiwen
 */
public class Rori {
    private RoriManager roriManager;

    public Rori() throws RoriException {
        roriManager = new RoriManager();
    }

    /**
     * Returns the output which is obtained from running the user's input through Rori.
     * @param input The users input.
     * @return The String which is obtained when calling RoriException.
     */
    public String getResponse(String input) {
        assert !input.equals("") : "Empty input";
        String output;
        try {
            output = roriManager.runRori(input);
        } catch (RoriException e) {
            output = e.getMessage();
        }
        return output;
    }

    public RoriManager getRoriManager() {
        return this.roriManager;
    }
    
}
