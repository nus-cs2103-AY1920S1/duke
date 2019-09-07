/**
 * Represents a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public String getResponse(String input) {
        try {
            ui.input(input);
            storage.save(ui.getTaskList().getList());
            return tasks.getOutput();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Creates a Duke with given filePath to load Tasks in the file.
     * @param filePath The file that saves the Tasks.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        ui = new Ui(tasks);
    }

    public String getWelcomeAndTasks() {
        return ui.welcomeMessage() + ui.taskListMessage();
    }
}
