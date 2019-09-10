import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /**
     * Represents the Main code for the Duke chatbot.
     * Will load a list of tasks from the .txt file and modify it
     * as the user keys in new tasks and manipulates existing ones
     * @throws IOException thrown if unable to load file
     */
    public Duke() throws IOException {
        String filePath = "data/duke.txt";
        ui = new UI();
        storage = new Storage(filePath);
        try {
            if (storage.load() != null) {
                tasks = new TaskList(storage.load());
            } else {
                throw new DukeException("Unable to load data from file");
            }
        } catch (DukeException exp) {
            ui.showErrorMsg(exp.getMessage());
        }
    }

    /**
     * Get response from Duke.
     */

    public String getResponse(String echo) {
        String response = "";
        try {
            Command cmd = Parser.parse(echo);
            response = cmd.execute(tasks, ui, storage);
        } catch (DukeException exp) {
            response = ui.showErrorMsg(exp.getMessage());
        }

        return response;
    }

}