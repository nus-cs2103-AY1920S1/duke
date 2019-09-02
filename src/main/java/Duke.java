/*import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Duke {

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        String filePath = "data/duke.txt";
        File data = new File(filePath);
        System.out.println("\n" +
                "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");
        TaskTracker tracker = new TaskTracker(data);
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            tracker.process(command);
            command = sc.nextLine();
        }
        String dataToOverwrite = tracker.end();
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(dataToOverwrite);
            fw.close();
        } catch (Exception err) {
            System.err.println(err);
        }
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}*/
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
