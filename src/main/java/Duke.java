import duke.DukeException.DukeException;
import duke.command.Command;
import duke.command.Parser;
import duke.taskHandler.Storage;
import duke.taskHandler.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Duke is the main class that launches the chat bot when it is run.
 */
public class Duke {

    /**
     * This is the path of the file where the tasks are saved to and loaded from.
     */
    private String filePath;

    /**
     * This is the storage class which does the saving and loading of the files.
     */
    private Storage storage;

    /**
     * This is the task list class that contains the list of tasks.
     */
    private TaskList tasks;

    /**
     * The ui class handles the output to the user.
     */
    private Ui ui;

    /**
     * Creates a new chat bot with a specified file path.
     * If the specified file contains pre-saved tasks, it will be loaded.
     * @param filePath The path of the file that is used to save and load the tasks.
     */
    public Duke(String filePath) {
        this.filePath = filePath;
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                Scanner sc = new Scanner(System.in);
                String fullCommand = sc.nextLine();
                ui.printLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks.getList(), ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                System.out.println("☹ OOPS!!! The command is invalid");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }
}

