public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(fileName);
        try {
            taskList = storage.displayTaskList();
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        String fullCommand = ui.readCommand();
        boolean isExit = false;
        while(!isExit) {
            ui.printBlankLine();

            try {
                Command command = Parser.parse(fullCommand);
                command.execute(ui, taskList, storage);
                isExit = Command.isExit;

            } catch (DukeException e) {
                System.out.println(e);
            } finally {
                if (!isExit) {
                    fullCommand = ui.readCommand();
                }
            }
        }
        ui.showFarewell();
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
