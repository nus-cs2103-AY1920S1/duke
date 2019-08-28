import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

//Assumption: reads word by word, additional arguments would be ignored
// Accepts empty task
public class Duke {
    private MyList taskList;
    //private Storage storage;
    private UserInterface ui;

    public Duke(String directory) {
        //storage = new Storage(directory);
        ui = new UserInterface();
        /*
        try {
            taskList = storage.loadList();
        } catch (DukeException e) {
            ui.printException(e.toString());
            taskList = new TaskList();
        }

         */
    }

    public static void main(String[] args) {
        Duke duke = new Duke("./data/");
        duke.run();
        duke.exit();
    }

    public void run() {
        ui.printIntro();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                //c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printException(e.toString());
            } finally {
                ui.printLine();
            }
        }
    }

    public void exit() {
        ui.exit();
    }
}

