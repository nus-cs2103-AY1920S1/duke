import java.util.Scanner;
import java.util.ArrayList; 
import java.text.ParseException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filepath){
        //try{
            ui = new Ui();
            storage = new Storage(filepath);
            tasks = new TaskList(storage.load());
        // } catch (DukeException e) {
        //     ui.showLoadingError();
        //     tasks = new TaskList();
        // }
    }
    
    
    public void run(){
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(tasks, ui, storage);
        while (sc.hasNextLine()){
            try{
                
                    parser.parse(sc.nextLine());
                
            }catch(DukeException e){
                System.out.println(e);
            }
        }
        sc.close();
    }
    
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
