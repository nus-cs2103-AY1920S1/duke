import java.io.*;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws Exception {
        ui = new Ui();
        storage = new Storage(filePath);
        try {

            tasks = new TaskList(storage.load());

        } catch (DukeException e) {
            ui.showLoadingError();
           tasks = new TaskList();
        }
    }

    public void run() throws FileNotFoundException {
        ui.run(tasks, storage);
    }



    public static void main(String[] args) throws Exception {

        new Duke("DukeOutput.txt").run();

    }
}
