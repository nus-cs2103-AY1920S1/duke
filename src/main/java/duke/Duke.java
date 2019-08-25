package duke;

import java.util.Scanner;
import java.util.ArrayList;

import duke.command.Command;
import duke.command.Commands;
import duke.task.Task;


public class Duke {
    private static String TASK_DATA_PATH = "../data/taskData.txt";

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public static void main(String[] args) {
        Duke duke = new Duke(TASK_DATA_PATH);
        duke.run();
    }

    private Duke(String dataFilePath) {
        storage = new Storage(dataFilePath);
        ui = new Ui(new Scanner(System.in));
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
                continue;
            }
        }
    }
}