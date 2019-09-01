/*
 * Main class responsible for running Duke Chatbot
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /*
     * Constructor of Duke class
     * @param filepath The directory of the text file for populating task list
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /*
     * Method to start up Duke Chatbot
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(storage, taskList, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("src/main/data/duke.txt").run();
    }


}

