/**
 * This program is Duke, which stores task for users.
 * 
 * @author Timothy Yu Zhiwen
 */
public class Duke {
    /**
     * This is the main for the program, Duke.
     * 
     * @param args For main
     * @throws DukeException Certain methods throws DukeException
     */
    public static void main(
            String[] args) throws DukeException {
        DukeManager dukeManager = new DukeManager();
        dukeManager.initializeDuke();
    }
}
