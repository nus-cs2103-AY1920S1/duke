import java.util.Scanner;

import utils.Storage;
import utils.TaskList;
import utils.Ui;
import utils.Parser;
import exceptions.DukeException;;

/**
 * this class is the main class of the application, it initializes the main components 
 * and passes them as references to the others.
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filepath){
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList(storage.load(), ui);
    }
    
    /**
     * runs every command-line command through the parser method 
     */
    
    public void run(){
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(tasks, ui, storage);
        while (sc.hasNextLine()){
            try{
                parser.parse(sc.nextLine());
            }catch(DukeException e){
                ui.print(e.getMessage());
            }
        }
        sc.close();
    }
    
    public static void main(String[] args) {
        new Duke("/Users/estherngo/Documents/elsa/2103/duke/src/main/java/data/duke.txt").run();
    }
}
