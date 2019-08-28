import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a Duke class which is used to run a taskList computer program
 * that allows users to store their various task (Deadline, Event, Things to do)
 * and store them in a text file. The program also allows them to delete and update
 * their list once they have completed it.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke class
     * Takes in a filePath and instantiate a UI, Storage and TaskList object.
     *
     * @param filePath an String file location
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
    }

    /**
     * Runs the entire program. Kick starts the entire computer duke Program
     *
     * @throws IOException
     */
    public void run() throws IOException {
        ui.initiate();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String inputInstruction = sc.nextLine();
            String inputCommand = Parser.getInputCommand(inputInstruction);
            if (inputCommand.equals("bye")) {
                System.out.println("___________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("___________________________________");
                break;
            } else {
                ui.executeInstructions(inputInstruction, inputCommand, storage, tasks);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        new Duke("D:\\NUS Computer Science\\CS2103T\\duke\\src\\main\\java\\duke.txt").run();
    }
}
