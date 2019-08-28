package seedu.duke;

/**
 * Main class of Duke application.
 */
public class Duke {
    private static final String DATA_PATH = "./duke_data.txt";

    private Storage storage;
    private Parser parser;

    /**
     * Constructor.
     */
    public Duke() {
        storage = new Storage(DATA_PATH);
        parser = new Parser(storage);
    }

    /**
     * Returns the response from the parser.
     * @param input The input from the user.
     */
    public String getResponse(String input) {
        return parser.process(input);
    }
}
