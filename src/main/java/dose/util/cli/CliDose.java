package dose.util.cli;

import dose.Dose;
import dose.command.Command;
import dose.task.TaskList;
import dose.util.Parser;
import dose.util.Storage;
import dose.util.UiMessage;
import dose.util.exception.DoseException;

public class CliDose implements Dose {
    Storage storage;
    TaskList tasks;
    String filePath = "data/tasks.txt";

    /** CLI implementation of Dose uses a Cli object to represent its UI. */
    Cli ui;

    /**
     * Creates a new instance of Dose, with the default filePath.
     */
    public CliDose() {
        ui = new Cli();
        storage = new Storage(filePath, ui);
        tasks = new TaskList();
    }

    /**
     * Runs Dose from the CLI. All output is displayed in the CLI.
     */
    public void run() {
        ui.showMessage(UiMessage.WELCOME);
        initializeStorage();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DoseException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Creates and runs a new instance of Dose from the CLI.
     * @param args Arguments supplied by the user.
     */
    public static void main(String[] args) {
        CliDose duke = new CliDose();
        duke.run();
    }

    /**
     * Attempts to import an existing task list.
     */
    public void initializeStorage() {
        try {
            TaskList tasksFromFile = storage.load();
            tasks = tasksFromFile;
        } catch (Exception e) {
            // temporary haxx
            e.printStackTrace();
        }
    }
}
