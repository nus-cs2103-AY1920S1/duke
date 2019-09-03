package duke.util;
/**
 * duke.util.Duke Class
 */
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private final String path = "./saved/taskList_history.txt";

    /**
     * Constructor for duke
     */
    public Duke() {
        storage = new Storage(path);
        ui = new Ui();
        parser = new Parser();
        try {
            if (!storage.historyExists()) {
                storage.createFile();
                tasks = new TaskList(new ArrayList<>());
            } else {
                tasks = new TaskList(storage.retrieveHistory());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the duke's response to a user input
     * @param input User input
     * @return Message to be displayed
     */
    public String getResponse(String input) {
        try {
            String[] inputParts = input.split(" ", 2);
            Command c = Parser.parse(inputParts);
            return c.execute(storage, ui, tasks);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DukeException e) {
            return e.getMessage() + "\nType 'commands' to view a list of commands you can use";
        }
        return "";
    }
}
