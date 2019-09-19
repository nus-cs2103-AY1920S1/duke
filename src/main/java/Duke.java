import java.io.File;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.getTasks());
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }

    /**
     * Returns response corresponding to user input.
     * @param input user command.
     * @return Response as String.
     */
    String getResponse(String input) {
        Parser parser = new Parser(input, ui, tasks);
        if (parser.getCommand().equals("bye")) {
            return ui.sayGoodbye();
        }
        try {
            return parser.doCommand();
        } catch (Exception e) {
            return "Something went wrong :( Please try again";
        }
    }

    /**
     * Helper method for Storage.updateTasks
     */
    public void updateTasksHelper() {
        String filePath = "C:\\Users\\johnn\\CS2103\\duke\\tasks.txt";
        boolean isUpdated = storage.updateTasks(filePath, tasks.getList());
        assert isUpdated : "file not updated";

    }


    public void run() {
        ui.greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            Parser parser = new Parser(sc.nextLine(), ui, tasks);
            if (parser.getCommand().equals("bye")) {
                ui.sayGoodbye();
                break;
            }
            try {
                parser.doCommand();
            } catch (Exception e) {
                System.out.println("Something went wrong :( Please try again");
            }
        }
        String filePath = "C:\\Users\\johnn\\CS2103\\duke\\tasks.txt";
        try {
            storage.updateTasks(filePath, tasks.getList());
        } catch (Exception e) {
            System.out.println("Tasks not saved");
        }
    }

    public static void main(String[] args)  {
        String filePath = "C:\\Users\\johnn\\CS2103\\duke\\tasks.txt";
        new Duke(filePath).run();
    }



}
