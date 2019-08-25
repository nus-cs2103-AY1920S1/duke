import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private TaskList taskList;
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
            taskList = storage.readData();
        } catch (FileNotFoundException e) {
            Ui.printException(e);
        }

        Ui.startOfInteractions();

        taskList = parser.parse(taskList);

        Ui.endOfInteractions();

        try {
            storage.writeData(taskList);
        } catch (IOException e) {
            Ui.printException(e);
        }
    }
}
