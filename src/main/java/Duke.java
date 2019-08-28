import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            // Initialise taskList with AL of tasks
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            // If file does not exist, just create new taskList
            ui.showMessage("File in given path does not exist yet. Creating new list of tasks...");
            taskList = new TaskList();
        }
    }

    public void run() throws IOException {
        ui.showGreetings();
        boolean inProgram = true;
        while (inProgram) {
            try {
                String userFullInput = ui.readUserInput();
                Command c = parser.parse(userFullInput, taskList);
                c.execute(taskList, ui, storage);
                inProgram = c.toContinueProgram();
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("F:\\CS2103\\duke\\data\\duke.txt").run();
    }

    public TaskList getTaskList() { return taskList; }

}