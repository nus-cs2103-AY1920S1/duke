public class Duke {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            //try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            //} catch (DukeException e) {
            //    ui.showError(e.getMessage());
            //} finally {
            //    ui.showLine();
            //}
        }
        return;
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}