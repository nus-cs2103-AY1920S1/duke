/**
 * The driver class of the entire Duke Project.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    private boolean toExit = false;

    /**
     * Creates Duke which read and writes to the given filepath.
     * @param filePath the location of the save file.
     */
    public Duke(String filePath) {
        assert true : "this should be true";
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.getData());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns the response of Duke when an input is given by the user.
     * @param input The user's input.
     * @return A string which is Duke's reponse
     */
    public String getResponse(String input) {
        String output = "";
        try {
            Command c = DukeParser.parse(input);
            output = c.execute(tasks, storage);
            toExit = c.isExit();
        } catch (DukeException e) {
            output = e.toString();
        } finally {
            return output;
        }
    }

    public boolean shouldExit() {
        return this.toExit;
    }

}
