import duke.command.Command;
import duke.command.ExitCommand;
import duke.component.Parser;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) throws FileNotFoundException {

        ui = new Ui();
        storage = new Storage(filePath);
        ui.showWelcomeScreen();
        taskList = new TaskList(storage.load());
    }

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

    public static void main(String[] args) {

        try {
            new Duke(System.getProperty("user.dir") + "\\data\\Duke.txt").run();
        } catch (Exception e) {
            Ui.printErrorMessage(e);
            return;
        }
    }
}