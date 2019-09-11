import commands.Command;
import logic.DukeException;
import logic.Parser;
import logic.Storage;
import logic.TaskList;
import logic.Ui;

import java.util.Scanner;

/**
 * Driver Class for Program.
 */
public class Duke {
    private Scanner sc = new Scanner(System.in);
    private String taskListPath;
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructor.
     */
    public Duke() {
        taskListPath = "./src/main/data/taskList.txt";
        storage = new Storage(taskListPath);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.loadStr(e.getMessage());
        }
    }

    /**
     * Continuously scans for User Input, Creates Command Objects and execute accordingly.
     */
    void run() {
        ui.greet();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            try {
                Command command = Parser.parseCommand(input);
                command.execute(tasks, ui, storage);
            } catch (DukeException e) {
                Ui.loadStr(e.getMessage());
            }
        }
    }

    String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            Ui.loadStr(e.getMessage());
        } finally {
            return Ui.getLoadedStr();
        }

    }

    String startMessage() {
        ui.greet();
        return Ui.getLoadedStr();
    }

}