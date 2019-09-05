package duke.task;

import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import duke.task.DukeException;
import duke.task.Parser;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Ui;

public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor function.
     * @param filePath The path to the file for tasks storage.
     */
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

    /**
     * Overloaded constructor function for JavaFX.
     */
    public Duke() {

    }

    /**
     * The main function to execute the program.
     */
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

    /**
     * The main function to execute the program in GUI mode.
     * @param stage The stage to display content in.
     */
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main entry point to the program.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke("../data/tasks.txt").run();
    }

    /**
     * This function does basic handling of the user's input and pass on the arguments to Parser.
     * @param next The next input by the user.
     * @throws DukeException When a program-specific exception has occurred.
     */
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
        } else if (next.startsWith("find")) {
            this.ui.printFind(Parser.handleFind(this.tasks, next));
        } else {
            Task task = Parser.handleItem(tasks, next);
            this.ui.printAdd(task, (this.tasks.getTasks().size()));
            this.storage.save(this.tasks);
        }
    }
}
