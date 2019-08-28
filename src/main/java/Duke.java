import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    // String Constants used for Duke output
    private Storage storage;
    private TaskList tasks;
    private Ui ui = new Ui();
    private int size;

    public Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadFile()); // add existing tasks from txt file onto ArrayList
            size = tasks.getSize(); // get existing size of list
            storage.closeFile();
        } catch (DukeException e) {
            ui.loadError();
            ui.createFile();
            ArrayList<Task> list = new ArrayList<>();
            tasks = new TaskList(list);
        }
    }

    public void run() {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Store command-line input as String
        String input = "";

        Storage storage = this.storage;
        TaskList tasks = this.tasks;
        Parser parser = new Parser();

        // Create new user interface object
        Ui ui = new Ui();

        // Read command-line input with Scanner
        Scanner scanner = new Scanner(System.in);

        // Initial opening introduction and prompt for user input
        ui.openingStatement();

        // Check for last statement
        while (!input.equals("bye")) {

            // Get entire line of input from command-line
            input = scanner.nextLine().trim(); //Remove blank space

            // Store whatever text entered, except "bye", exit loop
            if (input.equals("bye")) break;

            ui.separator();

            if (input.equals("list")) {
                ui.showList();
                tasks.printList();
            } else if (input.contains("done")) {

                try {
                    //Mark task as done
                    tasks.setDone(input);
                } catch (NullPointerException err) {
                    ui.invalidEntry();
                    continue;
                }

            } else if (input.contains("delete")) {

                try {
                    //Mark task as done
                    tasks.deleteTask(input);
                } catch (NullPointerException err) {
                    ui.invalidEntry();
                    continue;
                }
            } else if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {

                try {
                    String action = parser.parseAction(input);
                    ui.addTask();
                    tasks.addTask(action, input);
                } catch (DukeException err) {
                    System.out.println(err.getMessage());
                }

            } else {
                // Do not fit any commands
                try {
                    throw new DukeException(ui.invalidCommand());
                } catch (DukeException err) {
                    System.out.println(err.getMessage());
                    ui.separator();
                    System.out.println("");
                    continue;
                }
            }

            ui.separator();
            System.out.println();

            try {
                storage.saveFile(tasks, this.storage.getFilePath());
            } catch (DukeException e) {
                ui.saveError();
            }
        }
        // Closing statement
        ui.closingStatement();
    }

    public static void main(String[] args) {
        new Duke("list.txt").run();
    }
}

