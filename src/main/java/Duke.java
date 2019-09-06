import commands.Command;
import logic.*;

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
            Ui.printStr(e.getMessage());
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
                Ui.printStr(e.getMessage());
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return "Duke heard: " + input;
    }

}