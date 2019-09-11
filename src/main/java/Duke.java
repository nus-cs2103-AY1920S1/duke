import java.util.List;
import java.util.ArrayList;



public class Duke {

    private Ui ui;
    public static List<Task> lst = new ArrayList<>();


    public Duke()  {
        this.ui = new Ui();
    }

    public void run() {
        ui.printWelcomeMessage();
        while (ui.hasInputs()) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input);
                command.execute(lst, this.ui);
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
