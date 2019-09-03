package duke.task;

import java.util.Scanner;

import duke.task.DukeException;
import duke.task.Parser;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Ui;

public class Duke {
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

    public void run() {
        Scanner sc = new Scanner(System.in);
        this.ui.printHello();

        while (sc.hasNext()) {
            String next = sc.nextLine();

            if (next.equals("bye")) {
                this.ui.printBye();
                break;
            } else {
                try {
                    this.handleInput(next);
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                }
            }
        }

        sc.close();
    }

    public static void main(String[] args) {
        new Duke("../data/tasks.txt").run();
    }

    public void handleInput(String next) throws DukeException {
        if (next.equals("list")) {
            System.out.print(Parser.handleList(this.tasks));
        } else if (next.startsWith("done")) {
            Task task = Parser.handleDone(this.tasks, next);
            this.ui.printDone(task);
            this.storage.save(this.tasks);
        } else if (next.startsWith("delete")) {
            Task task = Parser.handleDelete(this.tasks, next);
            this.ui.printDelete(task, this.tasks.getTasks().size());
            this.storage.save(this.tasks);
        } else {
            Task task = Parser.handleItem(tasks, next);
            this.ui.printAdd(task, (this.tasks.getTasks().size()));
            this.storage.save(this.tasks);
        }
    }
}
