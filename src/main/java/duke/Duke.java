package duke;

import java.util.Scanner;
import java.util.ArrayList;

import duke.command.Command;
import duke.command.Commands;
import duke.task.Task;


public class Duke {
    private static String RECURSIVE_PARENT_DIR_NAME = "data";

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public static void main(String[] args) {
        Duke duke = new Duke(RECURSIVE_PARENT_DIR_NAME);
        duke.run();
    }

    private Duke(String dirName) {
        ui = new Ui(new Scanner(System.in));
        storage = new Storage(dirName, ui);
        tasks = new TaskList();
        storage.loadTasksToList(tasks);
    }

    private void run() {
        ui.printGreetingMsg();

        while (true) {
            try {
                String input = ui.readLine();
                ui.printLineDivider();
                Command command = Parser.parseCommand(input);
                command.execute(tasks, ui, storage);

                if (command.commandType == Commands.bye) {
                    break;
                }
            } catch (DukeExceptions ex) {
                ui.displayDukeException(ex);
            } finally {
                ui.printLineDivider();
                ui.printEmptyLine();
            }
        }
    }
}