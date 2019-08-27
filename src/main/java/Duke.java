import java.util.Scanner;

/**
 * Storage.
 * Remove line breaks from duke.txt. Done
 * Add constructor for duke for file path.
 * Initialise list with file path or new array list.
 * Abstract out as storage.load and constructor for TaskList. --> Requires TaskList to be done...
 * Handle wrong file path or empty file path.
 * Saving tasks (storage.save(TaskList, filePath))
 *
 * Command class
 * Parse to return command
 * c.execute(tasks, ui, storage)
 * c.isExit();
 *
 * Sort directory out...
 */

// TODO: update run method
public class Duke {
    private TaskList tasks;
    private UI ui;

    /**
     * Driver method.
     */
    public void run() {
        Command command;
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        tasks = new TaskList();
        ui = new UI();

        ui.showWelcomeMessage();

        while (!exit) {
            try {
                command = Parser.parse(sc.nextLine());
                command.execute(tasks, ui);
                exit = command.isExit();
            } catch (Exception e) {
                ui.showMessage(e.getMessage());
            }
        }
        sc.close();
    }

    /**
     * Main method.
     */
    public static void main(String[] args) {
        Duke d = new Duke();
        // Duke d = new Duke("Data/duke.txt");
        d.run();
    }
}
