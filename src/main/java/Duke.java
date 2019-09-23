public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke object.
     * @param filePath path of .txt file storing the tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.getTasks());
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }

    /**
     * Returns response corresponding to user input.
     * @param input user command.
     * @return Response as String.
     */
    String getResponse(String input) {
        Parser parser = new Parser(input, ui, tasks);
        try {
            return parser.doCommand();
        } catch (Exception e) {
            return "Something went wrong :( Please try again";
        }
    }

    /**
     * Helper method for Storage.updateTasks
     */
    public void updateTasksHelper() {
        boolean isUpdated = storage.updateTasks(tasks.getList());
        assert isUpdated : "file not updated";
    }
}
