import java.io.IOException;

/**
 * @author Timothy Yu Zhiwen
 */
public class Duke {
    /**
     * This program is Duke, which stores task for users.
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
