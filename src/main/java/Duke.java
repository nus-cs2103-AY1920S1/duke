import java.text.SimpleDateFormat;

public class Duke {
    // Configuration
    static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new Duke instance.
     * @param filePath Relative path of the save file
     */
    private Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showException(e);
            this.tasks = new TaskList();
        }
    }

    /**
     * Starts the Duke instance's execution.
     */
    private void run() {
        this.ui.showWelcome();

        String input = this.ui.getInput();
        while (input != null) {
            try {
                String command = Parser.extractCommand(input);

                switch (command) {
                case "bye": {
                    this.ui.showGoodbye();
                    System.exit(0);
                    break;
                }

                case "list": {
                    this.ui.showTasks(this.tasks.toString());
                    break;
                }

                case "done":
                case "delete": {
                    int taskId = Integer.parseInt(Parser.extractId(input));

                    if (command.equals("delete")) {
                        Task deletedTask = this.tasks.deleteTask(taskId);
                        this.ui.showTaskDeletion(deletedTask);
                    }

                    if (command.equals("done")) {
                        Task doneTask = this.tasks.markDone(taskId);
                        this.ui.showTaskDone(doneTask);
                    }

                    break;
                }

                case "find": {
                    String query = Parser.extractQuery(input);
                    this.ui.showQuery(this.tasks.query(query));
                    break;
                }

                case "todo":
                case "deadline":
                case "event": {
                    Task newTask = Parser.parseTask(input);
                    this.tasks.addTask(newTask);
                    this.ui.showTaskAdded(newTask);
                    break;
                }

                default: {
                    this.ui.showException(new DukeException("Sorry I do not understand. Please try again."));
                }
                }

                this.storage.persist(this.tasks.getTasks());
            } catch (DukeException e) {
                this.ui.showException(e);
            }

            input = this.ui.getInput();
        }

        this.ui.showGoodbye();
        System.exit(0);
    }

    /**
     * Main method.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
