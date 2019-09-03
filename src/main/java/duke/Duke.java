package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The Duke class runs Duke, the personal assistant chatbot
 * that helps a person keeps track of various things.
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Empty constructor for class Duke.
     */
    public Duke() {
        String filePath = "C:\\CS2103T\\duke\\data\\tasks.txt";
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            tasks = new TaskList();
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
     * Another constructor for class Duke.
     *
     * @param filePath File path for saving tasks in the hard disk.
     * @throws IOException Throws if an unpredicted error occurs.
     */
    /*public Duke(String filePath) throws IOException {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            File file = new File(filePath);
            file.createNewFile();
        } finally {
            ui = new Ui(tasks, storage);
        }
    }*/

    /**
     * Starts the program by calling the Ui.
     *
     * @throws IOException Throws if an unpredicted error occurs.
     */
    /*public void run() throws IOException {
        ui.scan(tasks, storage);
    }*/


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return ui.respond(input);
    }

    /*public static void main(String[] args) throws IOException {
        new Duke("C:\\CS2103T\\duke\\data\\tasks.txt");
    }*/
}
