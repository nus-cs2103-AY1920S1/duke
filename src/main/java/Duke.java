import main.*;
import task.*;
import command.*;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructor for the Duke App.
     * @param filepath String. filepath is the location of data storage.
     */
    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        this.tasks = new TaskList(storage.load());
        this.ui = new Ui();
    }

    /**
     * Runs the app.
     */
    private void run() {
        boolean isExit = false;
        ui.showWelcome();
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (InsufficientTaskArgumentException e){
                ui.showError(e.getMessage());
            } catch (InvalidTaskException e) {
                ui.showError(e.getMessage());
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                if (ui.hasLineToShow()) {
                    ui.showLine();
                }
            }
        }
        ui.showExit();
    }

    /**
     * Main method to instantiate Duke App and run it.
     * @param args String[] not in used.
     */
    public static void main(String[] args) {
        //More OOP
        Duke d = new Duke("../src/main/java/Dukedata.txt");
        d.run();
    }
}
