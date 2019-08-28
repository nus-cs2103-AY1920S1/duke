import java.util.LinkedList;
import java.util.Iterator;

public class Duke {
    
    public static void main(String[] args) {
        UI ui;
        TaskList tasks;
        FileHandler fileHandler;

        //Initialize FileHandler
        fileHandler = new FileHandler("../data", "save1.txt");
        
        //Try to load old data.
        try {
            tasks = fileHandler.loadData();    
        } catch (DukeException e) {
            //Does nothing for now
            tasks = new TaskList(new LinkedList<Task>());
        }
        
        //Start UI
        ui = new UI();
        ui.start(tasks, fileHandler);
    }
}
