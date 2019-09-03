public class Duke {
    private TodoList tasks;
    private Storage storage;
    private Ui ui;
    private static final String filePath = "/home/dingyuchen/cs2103/duke/src/main/data/duke.ser";


    private Duke(String s) {
        storage = new Storage(s);
        ui = new Ui();
        tasks = storage.load();
    }

    Duke() {
        this(filePath);
    }

    private void run() {
        this.ui.welcome();

        while (true) {
            try {
                Command command = this.ui.getUserInput();
                String response = command.run(tasks, storage);
                this.ui.printResponse(response);
                if (command.isExit()) {
                    break;
                }
            } catch (DukeException e) {
                this.ui.printErrorMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke(filePath);
        duke.run();
    }

    String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
