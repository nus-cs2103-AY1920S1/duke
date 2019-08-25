import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private ArrayList<Task> task = new ArrayList<>();
    private int counter = 0;
    private Storage storage;
    private Parser parser;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        parser = new Parser();
    }

    /**
     * Main method.
     * 
     * @param args arguments passed into main
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    private void run() {
        try {
            task = storage.readData();
            counter = task.size();
        } catch (FileNotFoundException e) {
            Ui.printException(e);
        }

        Ui.startOfInteractions();

        parser.parse(task, counter);

        Ui.endOfInteractions();

        try {
            storage.writeData(task);
        } catch (IOException e) {
            Ui.printException(e);
        }
    }
}
