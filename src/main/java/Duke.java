import customexceptions.DukeException;
import tasks.Storage;
import tasks.Task;
import tasks.TaskList;
import tasks.Ui;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents Duke bot.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructor for the Duke object.
     * @param path Path of the file where data is to be stored.
     * @param tasks List of tasks input by the user.
     * @throws DukeException
     */
    public Duke(String path, TaskList tasks) throws DukeException {
        storage = new Storage(path);
        this.ui = new Ui();
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Main program.
     * @param args
     */
    public static void main(String[] args) {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        try {
            new Duke("storage.txt", tasks).run();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Main running program.
     */
    public void run() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                Command command = new Command(input);
                command.execute(ui,tasks,storage);
                input = sc.nextLine();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
                input = sc.nextLine();
            }
        }
        ui.showBye();
    }


}
