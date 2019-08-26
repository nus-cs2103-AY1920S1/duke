import java.util.Scanner;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.nio.file.Files;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    private Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
    }

    private void run() {
        try {
            this.tasks = new TaskList(this.storage.loadFromFile());
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
        ui.showLine();
        ui.showWelcome();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.userInput();
                Command c = Parser.parse(userInput);
                if (c != null) {
                    c.execute(tasks, ui, storage);
                    isExit = c.isExit();
                }
            } catch (DukeException e) {
                System.err.println("Something went wrong: " + e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        try {
            this.storage.saveToFile(this.tasks.getAllTasks());
        } catch (DukeException e) {
            System.err.println("Something went wrong: " + e.getMessage());
        } finally {
            ui.showLine();
        }
    }

    public static Date dateFormatter(String date) throws DukeException {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Date parseDate = formatter.parse(date);
            return parseDate;
        } catch (ParseException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.run();
    }

}
