package duke;

import duke.exception.DukeException;
import duke.ui.Ui;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import duke.command.Command;

public class Duke {
    private TaskList tasks;
    private Saved savedFile;
    private Scanner scan;
    private Ui ui;

    /**
     * Creates a Duke object.
     * Initialise the storage, task list and ui objects.
     *
     * @param filePath the local path to the storage file
     * @throws IOException filePath is inaccessible or cannot be found
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        savedFile = new Saved(filePath);

        tasks = new TaskList(savedFile.loadData());
    }

    /**
     * Obtains user input from command line, parses it and executes the commands.
     * Shows errors to the user in case of invalid input.
     * Saves updated tasks back into the local file
     *
     * @throws IOException filePath is inaccessible or cannot be found
     */
    public void run() throws IOException {
        String input;
        Command cmd;

        ui.printIntro();

        Boolean isBye = false;
        while (!isBye) {
            try {
                input = ui.scanCmd();
                ui.printLine();
                cmd = Parser.parse(input);
                cmd.execute(tasks, ui, savedFile);
                isBye = cmd.isBye();
            } catch (DukeException error) {
                ui.printError(error.getMessage());
            }
            ui.printLine();
        }
        savedFile.saveToFile(tasks.getTaskArrayList());

    }

    /**
     * Required main method.
     *
     * @param args command line arguments
     * @throws IOException local file is inaccessible or cannot be found
     */
    public static void main(String[] args) throws IOException {
        new Duke("src/main/java/data.txt").run();
    }


}