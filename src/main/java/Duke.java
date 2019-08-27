import java.io.File;
import java.util.Scanner;

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
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        File file = new File("data");
        file.mkdir();
        new Duke("data/tasks.txt").run();
    }
}
