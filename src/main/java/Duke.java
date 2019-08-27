/**
 * Encapsulates a product named Duke, a personal assistant chat bot that helps a person to keep track of various things.
 * It can add, delete, and list task entries and mark them as done.
 */
public class Duke {
    private Parser parser;
    private UI ui;
    private DukeDatabase database;
    private TaskList taskList;

    /**
     * Run the entire program.
     *
     * @param args user input (not required).
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Start the Duke chat bot.
     */
    public void run() {
        // initialise the essential components of duke bot.
        parser = new Parser();
        ui = new UI();
        database = DukeDatabase.getDukeDatabaseInstance();
        taskList = database.getAllTasks();

        // Start running the bot
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                Command command = parser.parse(ui.readCommand());
                command.execute(taskList, ui, database);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.echo(e.getMessage());
            }
        }
    }
}
