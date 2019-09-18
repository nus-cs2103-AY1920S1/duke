package duke.command;

import java.io.IOException;

/**
 * Contains the main method.
 */
public class Duke{
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Opens the task and start the task update.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Start getting user input and update the storage.
     */
    public String run(String input) {
        String response = "";
        try {
            response = ui.start(new Parser(taskList, ui), input);
            storage.save(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!input.equals("bye")) {
            assert !response.equals("") : "Response is not updated";
        }
        return response;
    }
}