import java.io.*;

public class Duke {
    /**
     * Attributes for storage, tasks, and ui
     */
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * constructor for Duke
     * @param filePath is the filename of the text file
     * @throws Exception in case file is not able to load
     */
    public Duke(String filePath) throws Exception {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    /**
     * asks ui to run the application
     * @throws FileNotFoundException in case text file is not found
     */
    public void run() throws FileNotFoundException {
        ui.run(tasks, storage);
    }


    /**
     * entry point of Duke
     * @param args are standard feature
     * @throws Exception in case file is not found
     */
    public static void main(String[] args) throws Exception {
        new Duke("DukeOutput.txt").run();
    }
}
