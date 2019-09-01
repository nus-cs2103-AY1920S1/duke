import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;
import duke.util.Parser;

import java.util.Scanner;

/**
 * Driver class.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public static void main(String[] args) throws Exception {
        Duke duke = new Duke("./data/duke.txt");
        duke.run();
    }

    /**
     * Initializes the duke chatbot with a file path for storage purpose.
     * @param filePath The path to storage file.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new UI(new Scanner(System.in));
        try {
            tasks = new TaskList(storage.readFile());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Logic of the program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseUserInput(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}