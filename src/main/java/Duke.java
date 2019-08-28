import java.util.InputMismatchException;

public class Duke {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for Duke.
     * @param filePath is the data storage path
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.load());
    }

    /**
     * main method creates instance of Duke and calls run() method.
     * @param args is the method argument.
     */
    public static void main(String[] args) {

        Duke duke = new Duke("./data/duke.txt");
        duke.run();
    }

    /**
     * run() method will run the duke programme and continuously read input for user.
     */
    public void run() {
        this.ui.printWelcome();
        String cmd = "";
        boolean isExit = false;

        while (!isExit) {
            try {
                String command = this.ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();

            } catch (InputMismatchException e) {
                this.ui.printError(e);
            }
            this.ui.printLine();
        }
        this.closeDuke();
    }

    private void closeDuke() {
        this.storage.save(this.tasks.getTaskList());
        this.ui.printGoodbye();
    }

}
