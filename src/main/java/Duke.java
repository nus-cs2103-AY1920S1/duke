import java.io.IOException;

/**
 * @author Timothy Yu Zhiwen
 */
public class Duke {
    /**
     * This program is Duke, which is an artifical chat bot for storing tasks 
     * inputted by the user.
     * 
     * @param args
     * @throws DukeException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static void main(
            String[] args) throws DukeException, ClassNotFoundException, IOException {
        DukeManager dukeManager = new DukeManager();
        dukeManager.initializeDuke();
    }
}
