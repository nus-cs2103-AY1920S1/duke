import java.util.Scanner;

/**
 * Represents a duke object.
 */
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

    public static void main(String[] args) {

        Duke duke = new Duke("../data/duke.txt");
        duke.run();
    }

    /**
     * Runs the program.
     */
    public void run() {
        try {
            Scanner sc = new Scanner(System.in);
            ui.showWelcome();
            Parser.readList(sc, tasks);
            storage.save(tasks);
        } catch (DukeException ex) {
            System.out.println(ex);
        }
    }


}
