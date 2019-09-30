import java.util.Scanner;

import utils.Storage;
import utils.TaskList;
import utils.Ui;
import utils.Parser;

import exceptions.DukeException;

/**
 * this class is the main class of the application, it initializes the main components 
 * and passes them as references to the others.
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private final static String filePath = "src/main/java/data/duke.txt";

    /**
     * This is for the GUI, needs an empty constructor for it to work. 
     */

    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load(), ui);
        parser = new Parser(tasks, ui, storage);
    }

    
     /** initialises the application with references to all main components.
     * @param filepath computer relative / absolute path to txt file
     */

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList(storage.load(), ui);
    }
    
    public String getResponse(String input) {
        String output = "";
        try {
            output = parser.parse(input);
        } catch(DukeException e) {
            output = e.getMessage();
        }
        return output;
    }		     


    /**
     * runs every command-line command through the parser method.
     */
    
    public void run() {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(tasks, ui, storage);
        while (sc.hasNextLine()) {
            try {
                parser.parse(sc.nextLine());
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }
        sc.close();
    }
    
    // public static void main(String[] args) {
    //     new Duke("src/main/java/data/duke.txt").run();
    // }d
}
