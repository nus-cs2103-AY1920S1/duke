import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
    
    public static void main(String[] args) throws IOException {
        new Duke("/users/junhup/desktop/duke/src/main/java/duke.txt").run();
    }
    
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e);
            }
        }
    }
}


