package seedu.duke;

import java.util.Scanner;

public class Duke {

    private Ui ui;
    private Storage storage;
    private Parser parser;
    private Scanner sc;

    Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        sc = new Scanner(System.in);
        parser = new Parser(sc, storage);
    }

    void run() {
        ui.greeting();
        int response;
        do {
            ui.printLine();
            String command = sc.next();
            ui.printBreakLine();
            response = parser.process(command);
            if (response == -1) {
                ui.goodbye();
            }
            ui.printBreakLine();
        } while (response != -1);
    }

    public static void main(String[] args) {
        new Duke("./duke_data.txt").run();
    }
}
