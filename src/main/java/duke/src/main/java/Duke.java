import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Exception;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    // Initialising Duke
    // Do Loading of tasks, greeting message, set up tasks list
    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (LoadingErrorException | FileNotFoundException l){
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run(){
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args){
        new Duke("data/tasks.txt").run();
    }

}

// This class loads task from file and saves tasks to file.
class Storage{
    private Path targetFilePath;
    private HashMap<String, String> commandMap;

    public Storage(String filePath){
        // Convert string filePath to actual filepath and store in FilePath class
        targetFilePath = Paths.get(filePath);

        // Prepare Hash map to cross check command for reading/writing process
        commandMap = new HashMap<>();
        commandMap.put("T", "TODO");
        commandMap.put("D", "DEADLINE");
        commandMap.put("E", "EVENT");
    }

    // Method loads a file and process
    // Returns object TaskList
    public TaskList load() throws FileNotFoundException {
        File f = new File(targetFilePath.toString());
        // Handle exception **
        Scanner s = new Scanner(f);

        ArrayList<Task> list = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine();

            // Split the entire line of task using "\"
            // Assumes format of file to: "T | read book | 1"
            String[] lineData = line.split("\\|");
            for (int i = 0; i < lineData.length; i++) {
                lineData[i] = lineData[i].trim();
            }

        }
        return new TaskList(list);
    }


    // Helper function to determine Command
    private String findCommand(String s){
        if(commandMap.containsKey(s))
            return commandMap.get(s);
    }

}

// This class contains the task list and operations to work with task list.
class TaskList{
    ArrayList<>
}

abstract class Task{

}




// This class deals with deciphering the user commands
class Parser{

}

abstract class Command{

    public abstract void execute();

}

class LoadingErrorException extends Exception{
    public LoadingErrorException(){
        super("Error in loading tasks.");
    }
}