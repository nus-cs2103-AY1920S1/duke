import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        ToDoList work = new ToDoList();
        Ui ui = new Ui();

        ui.printMessage("Hello! I'm Duke\n     What can i do for you?");

        try {
            work.run();
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }

    }
}
