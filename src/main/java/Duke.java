import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filepath){
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList(storage.load(), ui);
    }
    
    
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
        new Duke("data/duke.txt").run();
    }
}
