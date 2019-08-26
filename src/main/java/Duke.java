import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    private static final String saveLoadFilePath = "listSaveData.txt";

    private Duke(String filepath) {
        // initialise variables
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filepath);
        try {
            tasks = storage.load();
        } catch (Exception e) {
            ui.print(e.getMessage());
            tasks = new TaskList();
        }
    }

    private void run() {

        try(Scanner scanner = new Scanner(System.in);
            PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8)
        ) {
            System.setOut(out);
            boolean active = true;

            ui.printLogo();
            ui.printHello();

            while (active && scanner.hasNextLine()) {
                active = parser.parse(scanner.nextLine(), tasks, ui, storage);
            }
        }
    }

    public static void main(String[] args) {
        new Duke(saveLoadFilePath).run();
    }
}