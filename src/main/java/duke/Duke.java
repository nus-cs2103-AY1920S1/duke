package duke;

import duke.command.Parser;
import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;

import java.text.ParseException;

/**
 * The main driver of Duke program.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for Duke.
     *
     * @param filePath a string which stores the directory of the data to be loaded
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        parser = new Parser(tasks, ui);
    }

    /**
     * Run the program using CLI.
     */
    public void run() {
        ui.printWelcome();

        // process input
        String text = ui.readLine();
        while (!text.equals("bye")) {
            try {
                parser.processLine(text); // add, delete, etc
            } catch (DukeException e) {
                System.err.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Input must be an integer.");
            } catch (ParseException e) {
                System.err.println(e.getMessage());
                System.err.println("Please use the format: dd/MM/yyyy hhmm");
            } catch (Exception e) {
                System.err.println("Something is wrong: " + e.getMessage());
            }
            text = ui.readLine();
        }

        // save to text file
        storage.save(tasks);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}