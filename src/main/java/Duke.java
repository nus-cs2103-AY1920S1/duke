import exceptions.DukeException;
import utilities.Parser;
import utilities.Storage;
import utilities.TaskList;
import utilities.Ui;
import command.Command;


/**
 * Main entry point.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * constructor for Duke.
     *
     * @param filePath is the filename of the text file
     *
     * @throws Exception in case file is not able to load
     */
    private Duke(String filePath) throws Exception {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * constructor for Duke when launcher is running.
     *
     * @throws Exception in case file is unable to load
     */
    Duke() throws Exception {
        ui = new Ui();
        storage = new Storage("DukeOutput.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    /**
     * asks ui to run the application.
     */
    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                //c.execute(tasks, ui, storage);
                String result = c.executeAsString(tasks, ui, storage);
                System.out.println(result);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                System.out.println("fml");
            } finally {
                ui.showLine();
            }
        }
        ui.showConclusion();
        ui.showLine();
    }


    /**
     * entry point of Duke.
     *
     * @param args are standard feature
     *
     * @throws Exception in case file is not found
     */
    public static void main(String[] args) throws Exception {
        new Duke("DukeOutput.txt").run();
        System.exit(0);
    }


    /**
     * to get response the display it.
     *
     * @param input is the input command
     *
     * @return the String output message
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.executeAsString(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showErrorFX(e.getMessage());
        } catch (Exception e) {
            return "fml";
        }
    }


}
