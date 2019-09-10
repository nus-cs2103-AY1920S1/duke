/**
 * The Duke class handles the input of the user.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke object.
     *
     * @param filePath A string representing the directory of the file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Generates a response based on the user's input.
     *
     * @param input A string of input provided by the user.
     * @return A string of Duke's response based on the input by user.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        String s = c.execute(tasks, ui, storage);
        return s;
    }
}