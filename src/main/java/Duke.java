import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
    }

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
