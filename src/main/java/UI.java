import java.util.Scanner;

public class UI{

    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    public UI(Storage storage, TaskList taskList){
        this.storage = storage;
        this.taskList = taskList;
    }

    public void run(){
        this.parser = new Parser(storage, taskList);

        boolean flag = true;
        
        while(flag){
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            
            flag = parser.parse(line);
            
        }

    }
}