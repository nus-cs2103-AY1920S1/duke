/**
 * The Duke Program is a bot that help users to manage their schedule.
 *
 * @author : Xu Tunan
 * @Version: v 1.0.0
 * @Since: 29/08/2019
 */
import duke.command.Storage;
import duke.command.Ui;
import duke.list.TaskList;
import duke.excaptions.IllegalDukeArgumentException;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     *This constructor is used to check if the text file exists and followed the standard.
     * If yes, it will read the data in the file and transform them to a taskList,
     * while if not, it will create a new taskList to store tasks.
     * @param filePath relative file path to the file that saved the data in the taskList
     */
    private Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui(storage);
        try {
            taskList = new TaskList(storage.textRead());
        } catch (IllegalDukeArgumentException e) {
            ui.showError("Loading Error!\nPlease check the file.");
            taskList = new TaskList();
        }
    }

    /**
     *This method aimed to make use of the readCommand() method in Ui class, and print out a bottom line at last.
     */
    public void run() {
        ui.readCommand();
        ui.showLine();
    }

    /**
     * This method makes use of showWelcome() method in Ui class to print out the greetings.
     */
    private void greeting() {
        ui.showWelcome();
    }

    /**
     * This is the main method to create a new Duke object and run it by steps,
     * which terminate the program when command "bye" appears.
     * @param args unused
     */
    public static void main(String[] args) {
        Duke duke = new Duke("Users/xutunan/duke/duke.txt");
        duke.greeting();
        duke.ui.showLine();
        while (!Ui.getIsExit()) {
            duke.run();
        }

    }
}


