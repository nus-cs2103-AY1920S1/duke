import duke.command.Command;
import duke.command.ExitCommand;
import duke.component.Parser;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Duke Object.
     * @param filePath path to the text file located on the hard disk.
     * @throws FileNotFoundException while input text file cannot be found.
     * @throws IOException while input text file cannot be created.
     */
    public Duke(String filePath) throws FileNotFoundException, IOException {

        ui = new Ui();
        storage = new Storage(filePath);
        ui.showWelcomeScreen();
        taskList = new TaskList(storage.load());
    }


    /**
     * Runs Duke Program.
     */
    public void run() {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            try {
                Command newCommand = Parser.retrieveCommandFromString(sc.nextLine());
                newCommand.executeCommand(taskList, storage, ui);
                if (newCommand instanceof ExitCommand) {
                    return;
                }

            } catch (Exception e) {
                ui.printErrorMessage(e);
            }
        }
    }

    /**
     * Runs the created Duke object by appending location of text file inside the constructor.
     * @param args arguments passing to main method
     */
    public static void main(String[] args) {

        try {
            new Duke(System.getProperty("user.dir")).run();
        } catch (Exception e) {
            Ui.printErrorMessage(e);
            return;
        }
    }
}