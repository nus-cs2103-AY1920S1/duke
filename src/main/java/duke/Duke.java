package duke;

import command.Command;
<<<<<<< .merge_file_a09940
=======
import task.TaskList;

>>>>>>> .merge_file_a07572
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

<<<<<<< .merge_file_a09940
=======
/**
 * Runs the program skeleton: Event flow.
 */
>>>>>>> .merge_file_a07572
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UserInterface ui;

<<<<<<< .merge_file_a09940
=======
    /**
     * Constructs Duke object.
     * @param filePath Specified file destination.
     */
>>>>>>> .merge_file_a07572
    private Duke(String filePath) {
        ui = new UserInterface();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException | ParseException e) {
            ui.showError(e.getMessage());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

<<<<<<< .merge_file_a09940
=======
    /**
     * Runs the logic flow of the system.
     */
>>>>>>> .merge_file_a07572
    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                storage.save(tasks.getAllTasks());
            } catch (DukeException | ParseException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

<<<<<<< .merge_file_a09940
=======
    /**
     * Main method to run the system.
     * @param args Arguments.
     */
>>>>>>> .merge_file_a07572
    public static void main(String[] args) {
        new Duke("data/dukeData.txt").run();
    }
}