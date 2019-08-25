public class Duke {

    private Ui ui;
    private TaskList taskList;

    public Duke() {
        this.ui = new Ui();
        this.taskList = new TaskList();
    }

    private void run() {
        this.ui.showWelcomeMessage();
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(this.taskList, this.ui);
                if (command.getCommandType().equals(CommandType.EXIT)) {
                    break;
                }
            } catch (DukeException e) {
                ui.showErrorMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
