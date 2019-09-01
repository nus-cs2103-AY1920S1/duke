import java.io.FileNotFoundException;
public class Duke {
    private Storage storage;
    private UI ui;
    private TaskList taskList;

    public Duke(String filepath) throws Exception {
        this.storage = new Storage(filepath);
        try {
            this.taskList = new TaskList(storage);
        } catch (FileNotFoundException error) {
            this.taskList = new TaskList();
        }

        this.ui = new UI(taskList);
    }

    public void run() throws Exception {
        ui.printIntro();
        storage.loadTasks();
        boolean programRunning = true;
        while(programRunning) {
            try {
                String input = ui.readLine();
                Command inputCommand = Parser.parse(input);
                inputCommand.execute(taskList, storage, ui);
            } catch (DukeException error) {
                ui.printError(error);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        new Duke("../Data/Duke.txt").run();
    }
}
