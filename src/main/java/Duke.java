/**
 * The main class for managing all the java files.
 */
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Instantiate a Duke object when a directory parameter is passed
     * into it. Will also instantiate the Ui, Storage and TaskList objects.
     * @param filePath the directory for the designated path to store the tasks.
     */
    private Duke (String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }

    }

    /**
     * Run the application Duke.
     */
    private void run() {
        parser = new Parser(tasks, ui);
        ui.showWelcome();
        while (true) {
            String command = ui.enterCommand();
            if (command.equals("bye")) {
                storage.save(parser.retrieveTasks());
                break;
            } else {
                parser.parse(command);
            }
        }
    }



    /**
     * Passed in the file path for the .txt file to instantiate the Duke object
     */
    public static void main(String[] args) {
        new Duke("/Users/kchensheng/Documents/NUS/Y2" +
                "/Sem1/CS2103/kwan_chen_sheng_duke/data/data.txt").run();
    }
}
