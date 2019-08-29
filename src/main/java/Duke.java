import java.util.Scanner;
import java.io.IOException;

public class Duke {

    TaskList taskList;
    Storage storage;
    Ui ui;

    /**
     * Creates a new Duke object
     * @param filePath Location of the local file where all tasks are stored
     * or to be stored
     * @throws IOException If the file path provided does not find a file
     * @throws DukeException If an unknown or unidentifiable command is stored within the tasks file
     */
    public Duke(String filePath) throws IOException, DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    /**
     * Drives the Duke program by providing the means to read user inputs
     * which are then processed into understandable commands which help Duke run.
     */
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
