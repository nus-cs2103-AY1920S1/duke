import jermi.Client;

/**
 * The Jermi program implements an application that keeps track of the user's tasks.
 *
 * @author Jermy
 * @version v1.0
 * @since 2019-08-25
 */
public class Jermi {

    /**
     * Initialises {@link Client} and runs it.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Client client = Client.initialise();
        client.run("data/jermi.txt");
    }
}
