package duke.;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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
    public duke.src.main.java.TaskList load() throws FileNotFoundException {
        File f = new File(targetFilePath.toString());
        // Handle exception **
        Scanner s = new Scanner(f);

        ArrayList<duke.src.main.java.Task> list = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine();

            // Split the entire line of task using "\"
            // Assumes format of file to: "T | read book | 1"
            String[] lineData = line.split("\\|");
            for (int i = 0; i < lineData.length; i++) {
                lineData[i] = lineData[i].trim();
            }

        }
        return new duke.src.main.java.TaskList(list);
    }


    // Helper function to determine Command
    private String findCommand(String s){
        if(commandMap.containsKey(s))
            return commandMap.get(s);
    }

}
