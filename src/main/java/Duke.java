import java.text.ParseException;
import java.util.Scanner;
import java.util.List;


public class Duke {
    private Storage storage;
    private Ui ui;
    private List<Task> tasks;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public Duke() {
        storage = new Storage();
        tasks = storage.readFromFile();
        ui = new Ui();
    }

    public void run() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\\\___|\n";

        System.out.println("Hello from\n" + logo);

        ui.welcomeMessage();

        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrors(e.getMessage());
            }
        }
    }
}
