import commands.Command;
import exceptions.DukeException;
import utils.Parser;
import utils.Storage;
import utils.TaskList;
import utils.Ui;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public static void main(String[] args) {
        Duke duke = new Duke("/home/abhinav/Desktop/2103T/duke/data/duke.txt");
        duke.run();
    }

    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.readFromFile());
        ui = new Ui();
    }

    public void run() {


        ui.welcomeMessage();

        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrors(e.getMessage());
            }
        }
    }
}
