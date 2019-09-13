package duke;

import duke.command.Command;
import duke.io.Parser;
import duke.io.Storage;
import duke.io.Ui;
import duke.location.Location;
import duke.task.TaskList;

import java.text.ParseException;

/**
 * CS2103 iP Deliverable, Duke.
 *
 * @author Ahmed Bahajjaj
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor creates ui, storage, and taskList.
     *
     * @param filePath Path for duke data file.
     */
    Duke(String filePath) {
        assert filePath != null : "Empty file path";
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = storage.loadTasks(ui);
        ui.out("What can I do for you?");
    }

    Duke(String taskFile, String placeFile) {
        assert taskFile != null : "Empty file path";
        assert placeFile != null : "Empty file path";
        ui = new Ui();
        storage = new Storage(taskFile, placeFile);
        storage.loadPlaces(ui);
        taskList = storage.loadTasks(ui);
        ui.out("What can I do for you?");
    }

    public String getOutput() {
        return ui.getDukeOut().toString();
    }

    /**
     * Provides duke function upon input.
     *
     * @param input user command
     * @return duke response
     */
    public String getResponse(String input) {
        ui.clear();
        try {
            Command command = Parser.parse(input);
            command.execute(taskList, ui, storage);
        } catch (ArrayIndexOutOfBoundsException ex) {
            ui.out("The details of an Event/Deadline cannot be empty.");
        } catch (UnsupportedOperationException ex) {
            ui.out("I'm sorry, but I don't know what that means.");
        } catch (NumberFormatException ex) {
            ui.out("Please only complete/delete tasks on the list.");
        } catch (ParseException ex) {
            ui.out("Please format date/time as 'dd-MM-yyyy HHmm'.");
        }
        return ui.getDukeOut().toString();
    }

    public void close() {
        storage.writeTasks(taskList);
        storage.writePlaces();
    }
}
