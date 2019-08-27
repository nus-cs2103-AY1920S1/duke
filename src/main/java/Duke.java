import myduke.command.Command;
import myduke.command.CommandParser;
import myduke.core.Ui;
import myduke.core.StorageManager;
import myduke.task.TaskList;

public class Duke {

    //Constants
    private static final String DATABASE_LOCATION = System.getProperty("user.dir") + "/data/duke.csv";

    //Class Variables
    private StorageManager storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the class Duke.
     */
    //Constructor
    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
        storage = new StorageManager(DATABASE_LOCATION, tasks, ui::log);
    }

    /**
     * To run Duke's program.
     */
    public void spin() {

        storage.tryLoadFromDataBase();

        ui.init();
        ui.welcomeUser();

        boolean continueChat = true;
        do {
            //Get query from user
            String userQuery = ui.waitForQuery();

            //Find and give Response
            try {
                Command cmd = CommandParser.create(userQuery);
                cmd.execute(tasks, ui, storage);
                continueChat = !cmd.shouldExit();
            } catch (Exception ex) {
                ui.logError(ex.getMessage());
            }

        } while (continueChat);

        ui.sayGoodBye();
        ui.shutdown();
        storage.tryWriteToFile();
    }


    public static void main(String[] args) {
        Duke myObj = new Duke();
        myObj.spin();
    }
}
