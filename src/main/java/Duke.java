import java.util.Scanner;

public class Duke {
    private Storage storage; //handles storage: file read file write
    private TaskList tasks; //aka has list of tasks + task handling
    private Ui ui; //handles i/o (ui) printing inputs etc for duke

    public Duke(String dataFilepath) {
        this.ui = new Ui();
        this.storage = new Storage(dataFilepath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                this.ui.showError(e.toString());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}
