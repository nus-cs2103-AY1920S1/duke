import java.io.FileNotFoundException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFromFile());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
    }

    String getResponse(String input) throws DukeException {
        if (input.equals("bye")) {
            return ui.byeMessage();
        } else {
            return Parser.parse(input).execute(tasks, ui, storage);
        }
    }
}