import command.Command;
import duke.Parser;
import duke.Storage;
import duke.Ui;
import task.TaskList;
import exception.DukeException;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The Duke programme implements a personal task assistant to which you can give commands.
 * It helps you store and manage to to-do tasks.
 *
 * @author Liu Zechu
 */
public class Duke {
    private static Storage storage;
    private static Ui ui;
    private static Parser parser;
    private static TaskList tasks;

    /**
     * Returns a Duke programme whose tasks are stored in the given file path.
     *
     * @param filePath File path of the file where the tasks are stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser(ui);

        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND");
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("/Users/liuzechu/Desktop/CS2103/project_duke/duke/data/duke.txt").run();
    }

    private void run() {
        ui.greet();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserInput();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.println("      OOPS!!!");
            }
        }

        exitDuke();
    }

    /**
     * Saves tasks into hard disk and exits the Duke programme.
     */
    private static void exitDuke() {
        String toSave = tasks.convertTasksToString();
        try {
            storage.writeToFile(toSave);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FILE NOT SAVED PROPERLY");
        }
    }
}