import task.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    public static final String LINE = "    ____________________________________________________________\n";
    // private static ArrayList<task.Task> tasks;
    private static Storage storage;
    private static Ui ui;
    private static Parser parser;
    private static TaskList tasks;

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