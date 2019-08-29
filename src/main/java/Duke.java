import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;
import duke.util.Parser;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public static void main(String[] args) throws Exception {
        Duke duke = new Duke("./data/duke.txt");
        duke.run();
    }

    public Duke(String filePath) throws Exception {
        this.storage = new Storage(filePath);
        this.ui = new UI(new Scanner(System.in));
        try {
            tasks = new TaskList(storage.readFile());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() throws Exception {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseUserInput(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}