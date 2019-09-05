public class Duke {
    private TaskList tasks;
    private Storage storage;

    /**
     * Initiates the main execution of the program.
     *
     * @param filePath The location of storage.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException ex) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns Duke's response after exectuing the user input.
     *
     * @param fullCommand User input.
     * @return Duke's response.
     */
    public String getResponse(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            return c.execute(tasks, storage);
        } catch (DukeException ex) {
            return ex.getMessage();
        }
    }
}
