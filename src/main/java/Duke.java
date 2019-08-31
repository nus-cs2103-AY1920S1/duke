import ui.UI;
import task.*;
import storage.Storage;
import exception.DukeException;
import parser.Parser;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Duke(String filePath){
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        }catch (DukeException e){
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run(){
        ui.showWelcome();
        String command = ui.readCommand();
        new Parser().parse(command, ui, tasks, storage.path);
        ui.showGoodByeMessage();
    }

    public static void main(String[] args) {
        new Duke("C:\\duke\\src\\data\\tasklist.txt").run();
    }
}