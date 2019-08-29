/**
 * Duke Class
 */
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Main method to run Duke
     * @param args
     */
    public static void main(String[] args) {
        new Duke("./saved/taskList_history.txt").run();
    }

    public Duke(String path) {
        storage = new Storage(path);
        ui = new Ui();
        parser = new Parser();
        try {
            if (!storage.historyExists()) {
                storage.createFile();
                tasks = new TaskList(new ArrayList<>());
            } else {
                tasks = new TaskList(storage.retrieveHistory());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Run method for Duke Object
     */
    private void run() {
        ui.welcomeMessage();
        Scanner sc = new Scanner(System.in);
        boolean shouldExit = false;
        while (!shouldExit) {
            try {
                String input = sc.nextLine();
                String[] inputParts = input.split(" ", 2);
                String command = inputParts[0];
                Command c = Parser.parse(inputParts);
                shouldExit = c.execute(storage, ui, tasks);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DukeException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
        sc.close();
    }

}
