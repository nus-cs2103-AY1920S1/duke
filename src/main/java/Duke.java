import Command.Command;
import Exceptions.DukeException;
import Utilities.Parser;
import Utilities.Storage;
import Utilities.TaskList;
import Utilities.Ui;
import javafx.scene.image.Image;


/**
 * Main entry point
 */
public class Duke /*extends Application*/{
    /**
     * Attributes for storage, tasks, and ui
     */
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    private Image user = new Image(this.getClass().getResourceAsStream("/images/daduke.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));


    /**
     * constructor for Duke
     * @param filePath is the filename of the text file
     * @throws Exception in case file is not able to load
     */
    public Duke(String filePath) throws Exception {
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
     * Dummy constructor
     */
    public Duke() throws Exception{
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
     * asks ui to run the application
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                System.out.println("fml");
            }finally {
                ui.showLine();
            }
        }
        ui.showConclusion();
        ui.showLine();
    }


    /**
     * entry point of Duke
     * @param args are standard feature
     * @throws Exception in case file is not found
     */
    public static void main(String[] args) throws Exception {
        new Duke("DukeOutput.txt").run();
        System.exit(0);
    }



    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
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
