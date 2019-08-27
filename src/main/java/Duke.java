import java.util.Scanner;

/**
 * Main driver class for the Duke program.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Scanner scanner;
    private Parser parser;

    /**
     * Initializes the Duke program.
     * @param filepath The file path of the save file.
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.taskList = new TaskList(storage);
        this.parser = new Parser(taskList);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main program loop of the program.
     */
    public void run() {
        ui.welcomeMessage();
        String command = "";
        while (!command.equals("bye")) {
            command = scanner.nextLine();
            parser.parse(command);
        }
    }

    public static void main(String[] args) {
        new Duke("D:/Marcus Folder/SCHOOL STUFF/YEAR 2/CS2103T/duke/data/duke.txt").run();
    }
}
