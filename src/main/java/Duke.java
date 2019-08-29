import java.io.IOException;
import java.util.InputMismatchException;
import java.util.ArrayList;

public class Duke {
    private TaskList taskList;
    private Storage storage;
    private UI ui;
    private boolean isExit;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new UI();
        isExit = false;
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList(new ArrayList<Task>());
        }
    }

    public void run() {
        ui.greet();
        while (!isExit) {
            try {
                String stringCommand = ui.readCommand();
                Command command = new Parser().parse(stringCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (InputMismatchException e) {
                ui.printErrorMessage(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.printErrorMessage(e.getMessage());
            } catch (IllegalArgumentException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("C:\\Users\\hooncp\\Desktop\\duke\\data\\data.txt");
        duke.run();
    }
}