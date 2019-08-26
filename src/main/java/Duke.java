import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static final String FILEPATH = "/Users/jiangyuxin/Documents/sem1/cs2103/duke/data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException|IllegalDescriptionException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        Scanner in = new Scanner(System.in);
        while (true) {
            String command = ui.readCommand(in);
            if (parser.isTerminatingCommand(command)) {
                try {
                    storage.store(tasks);
                } catch (IOException e) {
                    ui.showStoringError(e);
                }
                ui.sayGoodBye();
                break;
            } else {
                try {
                    parser.parseCommand(command, tasks, ui);
                } catch (Exception e) {
                    ui.showParsingError(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Duke(FILEPATH).run();
    }
}