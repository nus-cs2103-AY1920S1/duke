import task.Todo;
import task.Deadline;
import task.Event;

import java.util.Scanner;

public class Duke {

    private static final String DATA_FILE_TASKS = "./data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void saveTasks() {
        try {
            storage.save(tasks.dump());
        } catch (DukeException e) {
            ui.showSavingError();
        }

    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            Parser parser = new Parser(fullCommand);
            String cmdInput = parser.getInput();
            String cmdKeyword = parser.getKeyword();
            String cmdArgs = parser.getArgs();
            String cmdBeforeSlashArgs = parser.getBeforeSlashArgs();
            String cmdSlashKeyword = parser.getSlashKeyword();
            String cmdSlashArgs = parser.getSlashArgs();

            try {
                if (cmdInput.equals("bye")) {
                    isExit = true;
                } else if (cmdInput.equals("list")) {
                    tasks.listTasks();
                } else if (cmdKeyword.equals("done")) {
                    tasks.finishTask(cmdArgs);
                } else if (cmdKeyword.equals("delete")) {
                    tasks.deleteTask(cmdArgs);
                } else if (cmdKeyword.equals("todo")) {
                    if (cmdArgs == null) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    tasks.addTask(new Todo(cmdArgs));
                } else if (cmdKeyword.equals("event")) {
                    if (cmdSlashKeyword == null || !cmdSlashKeyword.equals("at")) {
                        throw new DukeException("An event must have an 'at' date or time.");
                    }
                    tasks.addTask(new Event(cmdBeforeSlashArgs, cmdSlashArgs));
                } else if (cmdKeyword.equals("deadline")) {
                    if (cmdSlashKeyword == null || !cmdSlashKeyword.equals("by")) {
                        throw new DukeException("A deadline must have a 'by' date or time.");
                    }
                    tasks.addTask(new Deadline(cmdBeforeSlashArgs, cmdSlashArgs));
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }

        ui.showGoodbye();
        saveTasks();
    }

    /**
     * This is the main method and entry point for the Duke program.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new Duke(DATA_FILE_TASKS).run();
    }
}
