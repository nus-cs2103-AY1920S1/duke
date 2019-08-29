package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.DukeException;
import seedu.duke.util.Parser;
import seedu.duke.util.Storage;
import seedu.duke.util.TaskList;
import seedu.duke.util.UI;

import java.util.Scanner;

public class Duke {
    private UI ui;
    private Storage storageHandler;
    private TaskList taskList;
    private static final String savedPath = "C:\\Users\\drago\\Documents\\MEGA\\Work\\Uni\\Year 2\\CS2103T\\duke\\data\\duke.txt";

    public Duke(String savedPath) {
        ui = new UI();
        storageHandler = new Storage(savedPath);
        try {
            taskList = new TaskList(storageHandler.loadFromFile());
        } catch (DukeException ex) {
            ui.cannotLoad();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.greet();

        Scanner sc = new Scanner(System.in);
        String command;
        boolean isExit = false;
        while (!isExit) {
            try {
                command = sc.nextLine();
                Command c = Parser.parse(command);
                c.execute(taskList, ui, storageHandler);
                isExit = c.isExit();
            } catch (DukeException ex) {
                ui.showError(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke(savedPath).run();
    }

}
