/**
 * This program reads in command, adds new tasks into the task lists, changes the task
 * status when it is done, and delete the tast according to the command.
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    /** 3 attributes.
     * ui deals with interactions with the user.
     * storage deals with loading tasks from the file and saving tasks in the file.
     * tasks contains the task list e.g., it has operations to add/delete tasks in the list.
     */
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Loads the data from given file path.
     *
     * @param filePath filepath of the data of previous task lists.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Greets users, reads in inputs, processes the command, prints the outputs.
     */
    public void run() {
        ui.greet();
        String fullCommand = ui.readInput(new Scanner(System.in));

        while (!fullCommand.equals("bye")) {
            try {
                Parser parser = new Parser(fullCommand);
                boolean valid = parser.checkValidity();

                // Proceed only if the full command is valid.
                if(valid) {
                    String commandType = parser.getCommandType();

                    // Operate according to the command type.
                    switch (commandType) {
                    case "list":
                        tasks.showTasks();
                        break;
                    case "done":
                        tasks.doneTask(parser.getIndex());
                        storage.updateFile(tasks);
                        break;
                    case "delete":
                        tasks.deleteTask(parser.getIndex());
                        storage.updateFile(tasks);
                        break;
                    case "find":
                        tasks.findTask(parser.getKeyword());
                        break;
                    case "todo":
                        tasks.addTodo(parser.getActivityNameWithoutTime(),
                                false);
                        storage.updateFile(tasks);
                        break;
                    case "deadline":
                        tasks.addDeadline(parser.getActivityNameWithTime(),
                                parser.getDeadline(), false);
                        storage.updateFile(tasks);
                        break;
                    case "event":
                        tasks.addEvent(parser.getActivityNameWithTime(),
                                parser.getTime(), false);
                        storage.updateFile(tasks);
                        break;
                    }
                }
            } catch (DukeException | IOException ex) {
                System.out.println(ex.getMessage());
            }

            // Scans the next command line.
            fullCommand = ui.readInput(new Scanner(System.in));
        }

        ui.sayBye();
    }

    /**
     * Creates a new Duke object and passes in the file path to run the program.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("../../../data/tasks.txt").run();
    }
}