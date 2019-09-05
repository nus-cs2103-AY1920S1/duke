import java.io.IOException;
import java.util.Scanner;

/**
 * Duke is the main body of the program. It takes in the
 * user's input and processes it accordingly.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(ui);
        String input;

        ui.showWelcome();

        while (sc.hasNextLine()) {
            try {
                input =sc.nextLine();
                parser.parseCommand(input, tasks);
                storage.update(tasks);

                if (parser.isExit()) {
                    break;
                }

            } catch (DukeException e) {

                ui.showException(e);
                input = sc.nextLine();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        ui.showGoodbye();
    }


    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}