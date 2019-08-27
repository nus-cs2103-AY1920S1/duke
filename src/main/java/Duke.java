import customexceptions.DukeException;
import tasks.Storage;
import tasks.Task;
import tasks.TaskList;
import tasks.Ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Duke(String path, TaskList tasks) throws DukeException {
        storage = new Storage(path);
        this.ui = new Ui();
        this.tasks = new TaskList(storage.load());
    }

    public static void main(String[] args) {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        try {
            new Duke("storage.txt", tasks).run();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

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
