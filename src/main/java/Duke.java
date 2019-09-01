import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
        } catch (IOException e) {
            ui.showLoadingError();
        }
    }

    public void run() {

        ui.showWelcome();
        ui.showTopBorder();
        System.out.println("\n\tHere are the tasks in your list: ");
        ui.printTasks(tasks.getTaskList());
        ui.showBottomBorder();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException e) {
                System.out.println("Input / Output error!");
            }
        }

    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}