import java.util.Scanner;

/**
 * Main class of the application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Setting up several classes ready to be called.
     *
     * @param filePath the location of the file where the task is stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Executes and runs the main program.
     */
    public void run() {
        ui.welcomeMessage();

        Scanner sc = new Scanner(System.in);
        String inputText = sc.nextLine();

        while (!inputText.equals("bye")) {
            try {
                Parser.parse(tasks, ui, inputText, storage);
                inputText = sc.nextLine();
            } catch (Exception err) {
                System.out.println("[Exception]" + err);
            }
        }

        ui.exitMessage();
    }

    // Program starts here
    public static void main(String[] args) {
        new Duke("C:/Users/User/Desktop/duke.txt").run();
    }
}
