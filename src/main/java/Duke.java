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
        } catch (DukeException e) {
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void run() {
        ui.greet();
        Parser parser = new Parser(storage, tasks, ui);
        parser.parse();
    }

    public static void main(String[] args) {
        new Duke("../data/duke.txt").run();
    }
}
