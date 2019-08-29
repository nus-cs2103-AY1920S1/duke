import java.util.Scanner;
import java.io.IOException;

public class Duke {

    TaskList taskList;
    Storage storage;
    Ui ui;

    public Duke(String filePath) throws IOException, DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showGreetingMessage();
        String input = scanner.nextLine();
        Parser parser = new Parser();
        while (! input.equals("bye")) {
            try {
                parser.processCommand(input, taskList, ui, storage);
            } 
            catch (Exception e) {
                System.err.println(e);
            }
            finally {
                input = scanner.nextLine();
            }
        }
        ui.showGoodbyeMessage();
        scanner.close();
    }
    public static void main(String[] args) throws Exception {
        Duke duke = new Duke("/Users/abhimanyadav/Desktop/Duke/duke/src/main/java/dukeTasks.txt");
        duke.run();
    }
}
