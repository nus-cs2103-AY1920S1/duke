import command.Command;
import main.*;

import java.util.Scanner;

/**
 * Main class
 */
public class Duke {


    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.dukeEcho(e.getMessage());
            tasks = new TaskList();
        }
    }

    private void run() {

        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("Enter filepath for task list:");
        Scanner sc = new Scanner(System.in);
        String filePath = sc.nextLine();
        Duke duke;
        if (filePath.equals("default")) {
            duke = new Duke("/Users/zhangxuan/Desktop/CS2103/duke/data/tasks.txt");
        } else {
            duke = new Duke(filePath);
        }

        duke.run();
    }
}