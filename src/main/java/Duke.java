import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcomeMessage();

        Scanner sc = new Scanner(System.in);
        String inputText = sc.nextLine();

        while (!inputText.equals("bye")) {
            try {
                Parser.parse(tasks, ui, inputText, storage);
                inputText = sc.nextLine();
            } catch (Exception err) {
                System.out.println(err);
            }
        }

        ui.exitMessage();
    }

    public static void main(String[] args) {
        new Duke("C:/Users/User/Desktop/duke.txt").run();
    }
}
