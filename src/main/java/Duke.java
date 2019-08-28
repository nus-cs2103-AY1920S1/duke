import java.io.FileNotFoundException;

/**
 * Represents Duke, a personal chatbot assistant.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Creates Duke with the specified file path.
     * @param filePath The path to the tasks list file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Starts the Duke program.
     */
    public void run() {
        ui.hello();
        ui.takeInUserInput();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("src/main/java/tasklists.txt");
        duke.run();
    }

}
