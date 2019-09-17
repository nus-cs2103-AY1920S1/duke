package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 * The Duke class creates Duke, the personal assistant chatbot
 * that helps a person keeps track of various things.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for class Duke.
     */
    public Duke() {
        String filePath = "data/duke.txt";
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException | ParseException e) {
            tasks = new TaskList(); // if there are no tasks to load from tasks.txt or tasks.txt is corrupted
        } catch (FileNotFoundException e) {
            File file = new File(filePath);
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            ui = new Ui(tasks, storage);
        }
    }

    /**
     * Returns a response from the given input by calling the respond method in the Ui class.
     *
     * @param input The input from user.
     * @return string The response from duke.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            response = ui.respond(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
