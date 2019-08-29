import task.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The Duke programme implements a personal task assistant to which you can give commands.
 * It helps you store and manage to to-do tasks.
 *
 * @author Liu Zechu
 */
public class Duke {
    public static final String LINE = "    ____________________________________________________________\n";
    // private static ArrayList<task.Task> tasks;
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
        ui.getUserInput(parser);
    }

    /**
     * Saves tasks into hard disk and exits the Duke programme.
     */
    public static void exitDuke() {
        String toSave = tasks.convertTasksToString();
        try {
            storage.writeToFile(toSave);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FILE NOT SAVED PROPERLY");
        }
    }

    public static TaskList getTasks() {
        return tasks;
    }
}