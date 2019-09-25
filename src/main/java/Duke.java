import java.util.ArrayList;
import java.io.File;

import commands.Command;
import exception.DukeException;
import parser.Parser;
import storage.Storage;
import ui.Ui;
import tasks.Task;
import tasks.TaskList;

public class Duke {
    // String Constants used for Duke output
    private Storage storage;
    private TaskList tasks;
    private Ui ui = new Ui();
    private int size;


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        StringBuilder sb = new StringBuilder();
        try {
            Command c = Parser.parse(input);
            sb.append(c.execute(tasks, ui, storage) + "\n");
        } catch (DukeException err) {
            sb.append(err.getMessage());
        }
        return sb.toString();
    }

    /**
     * Instantiates a new Duke object.
     * Load an existing .txt file for the list of tasks stored.
     *
     * @param filePath The file path of the list .txt file
     */
    public Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadFile()); // add existing tasks from txt file onto ArrayList
            size = tasks.getSize(); // get existing size of list
            storage.closeFile();
        } catch (DukeException e) {
            ui.loadError();
            ui.createFile();
            ArrayList<Task> list = new ArrayList<>();
            tasks = new TaskList(list);
            File file = new File(filePath); // create new .txt file with provided filepath
            try {
                file.createNewFile();
            } catch (Exception err) {
                System.out.println("Unable to create new file."); // Unlikely
            }
        }
    }

    /**
     * Run the Duke application.
     * Accepted commands: list, done, delete, find
     * Accepted tasks: todo, event, deadline
     */
    public void run() {

        System.out.println(ui.welcomeStatement());
        boolean isExit = false;
        Storage storage = this.storage;
        TaskList tasks = this.tasks;

        // Initial opening introduction and prompt for user input
        System.out.println(ui.openingStatement());
        try {
            Command c = Parser.parse("remind");
            System.out.println(c.execute(tasks, ui, storage));
        }
        catch (DukeException err) {
            System.out.println("No reminders.");
        }
        while (!isExit) {
            try {
                String input = ui.readCommand();

                Command c = Parser.parse(input);
                System.out.println(c.execute(tasks, ui, storage));
                isExit = c.isExit();
            } catch (DukeException err) {
                System.out.println(err.getMessage());
            }
            ui.nextCommand();

            try {
                storage.saveFile(tasks, storage.getFilePath());
            } catch (DukeException e) {
                ui.saveError();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("list.txt").run();
    }
}

