import trackr.command.Command;
import trackr.exception.TrackrException;
import trackr.parser.Parser;
import trackr.storage.Storage;
import trackr.tasklist.TaskList;
import trackr.ui.Ui;

import java.io.FileNotFoundException;


public class Trackr {

    /**
     * Deals with loading tasks from the file and saving tasks in the file.
     */
    private Storage storage;

    /**
     * Contains the task list.
     */
    private TaskList tasks;

    /**
     * Deals with interactions with the user.
     */
    public Ui ui;

    /**
     * This is the class constructor assigning a filepath to the Duke object.
     */
    public Trackr() {
        ui = new Ui();
        storage = new Storage("C:/Users/Shawn Lee/Desktop/UNI FILES/Y2S1/CS2103T/duke/data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException ex) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (TrackrException e) {
            return e.getMessage();
        }
    }
}
