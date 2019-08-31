import java.util.Scanner;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.taskList = new TaskList(storage, ui);
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        boolean isExit = false;
        while (!isExit && sc.hasNextLine()) {
            String input = sc.nextLine();
            try {
                Command command = Parser.parseCommand(input);
                command.execute(taskList, ui);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }

        sc.close();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
