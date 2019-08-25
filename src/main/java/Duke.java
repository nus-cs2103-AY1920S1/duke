import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.ParseException;


public class Duke {

    /**
     * the main class, the Duke bot.
     */
    static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * constructor.
     * @param filePath of the file
     * @throws IOException when input is different from required.
     * @throws ParseException when input is different from required.
     */
    private Duke(String filePath) throws IOException, ParseException{
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * runs the bot and process inputs from users.
     * @throws ParseException if format of date is not the same as required.
     * @throws IOException when input is different from required.
     */
    private void run() throws ParseException, IOException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
                System.out.println();
            }
        }
    }

    public static void main(String[] args) throws IOException, ParseException{
        new Duke("duke.txt").run();
    }
}
