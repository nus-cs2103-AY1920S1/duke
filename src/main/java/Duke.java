public class Duke {

    private Ui ui;
    private TaskList taskList;


    public Duke()  {
        this.ui = new Ui();
        this.taskList = new TaskList();
    }

    public void run() {
        ui.printWelcomeMessage();
        while (ui.hasInputs()) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input);
                command.execute(taskList, this.ui);
                if (command.getCommandType().equals(CommandType.EXIT)) {
                    break;
                }
            } catch (IllegalArgumentException | DukeException error2 ) {
                ui.printErrorMessage(error2);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
   
}
