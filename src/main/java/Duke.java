import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public void run() {
        ui.printIntro();

        Scanner scanner = new Scanner(System.in);

        boolean run = true;
        int index;

        while (run) {
            try {
                String[] userInput = Parser.parseUserInput(scanner.next());
                String command = userInput[0];
                String[] params = Arrays.copyOfRange(userInput, 1, userInput.length);

                switch (command) {
                case "bye":
                    ui.printGoodbye();
                    run = false;
                    break;

                case "list":
                    ui.printToUser(taskList.list());
                    break;

                case "done":
                    try {
                        index = Integer.valueOf(params[0]);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("You need to specify a task ID to mark as done.");
                    }
                    ui.printToUser(taskList.markAsDone(index));
                    break;

                case "delete":
                    try {
                        index = Integer.valueOf(params[0]);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("You need to specify a task ID to delete.");
                    }
                    ui.printToUser(taskList.delete(index));
                    break;

                case "todo":
                    ui.printToUser(taskList.createTodo(params));
                    break;

                case "deadline":
                    ui.printToUser(taskList.createDeadline(params));
                    break;

                case "event":
                    ui.printToUser(taskList.createEvent(params));
                    break;

                default:
                    throw new DukeException("I'm sorry, but I don't know what that means.");

                }
            } catch (DukeException e) {
                ui.printErrToUser(e);
            }

        }
    }

    public Duke (String filepath) {
        storage = new Storage(filepath);
        taskList = new TaskList(storage);
        ui = new Ui();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
