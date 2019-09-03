import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Represents the personal assistant and contains the main method.
 */
public class Duke {

    private TaskList list;
    private Ui ui;
    private Parser parser;
    private Storage storage;

    /**
     * Creates a duke object which stores data in the specified path .
     * @param s the absolute path of the file where the data is stored.
     */
    public Duke(String s) {
        list = new TaskList();
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(s);
    }

    /**
     * Contains the flow of the program execution.
     * @throws ParseException if date of event class not in specified format.
     * @throws IOException if file not found or other input output exceptions
     */
    public void run() throws ParseException, IOException {
        Scanner scan = new Scanner(System.in);
        this.ui.Greet();
        File f = new File("../Duke/data/Duke.txt");
        Scanner sca = new Scanner(f);
        Scanner scanner = new Scanner(System.in);
        while (sca.hasNext()) {
            String dat = sca.nextLine();
            this.parser.readTask(dat,this.list);
        }
            this.parser.readUserCommand(this.ui,this.list,this.storage);
    }

    public static void main(String[] args) throws DukeException, IOException, ParseException {
        new Duke("../Duke/data/Duke.txt").run();
    }
}
