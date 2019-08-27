import java.util.Scanner;

/**
 * Represents a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke with given filePath to load Tasks in the file.
     * @param filePath The file that saves the Tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the Duke.
     */
    public void run() {
        ui.showWelcome();
        ui.showLine();
        ui.showTaskList(tasks);
        ui.showLine();

        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        while (!str.equals("bye")) {
            ui.showLine();
            try {
                tasks = ui.input(str, tasks);
            } catch (Exception e) {
                System.err.println("\t" + e.getMessage());
            }
            ui.showLine();
            str = sc.nextLine();
        }

        storage.save(tasks.getList());

        ui.showGoodbye();
    }

    public static void main(String[] args) {

        new Duke("C:\\Users\\jietung\\Desktop\\duke\\src\\main\\data\\duke.txt").run();

    }
}
