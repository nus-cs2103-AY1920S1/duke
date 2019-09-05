import command.Command;
import task.TaskList;
import util.Parser;
import util.Storage;
import util.Ui;

public class Duke {

    private TaskList tasks;
    private Ui ui;

    //    public Duke(String filePath) {
//        ui = new Ui();
//        storage = new Storage(filePath);
//        try {
//            tasks = new TaskList(storage.load());
//        } catch (DukeException e) {
//            ui.showLoadingError();
//            tasks = new TaskList();
//        }
//    }
    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
    }

    public void run() {
        Ui.welcomeMsg();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readInput();
                Ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.executeCommand(tasks, new Storage());
                isExit = c.isExit();
            } catch (Exception e) {
            } finally {
                Ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
