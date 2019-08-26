import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    final static String FILE_LOCATION = System.getProperty("user.dir") + "/data/duke.txt";
    final static List<String> availableCommands = Arrays.asList("bye", "list", "done", "todo", "event", "deadline", "delete");

    private UI ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;

    private Scanner scanner = new Scanner(System.in);

    public Duke(String filePath) {
        ui = new UI();
        taskList = new TaskList(ui);
        parser = new Parser(availableCommands);
        storage = new Storage(taskList, filePath);
        try {
            storage.readTask();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        ui.greet();

        while(scanner.hasNext()){
            String command = scanner.nextLine();

            try {
                parser.parseCommand(ui, storage, command, taskList);
            } catch (DukeException e) {
                ui.horizontalLine();
                System.out.println(e.getMessage());
                ui.horizontalLine();
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(FILE_LOCATION).run();
    }
}
