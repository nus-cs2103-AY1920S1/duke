import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class FileHandler {
    
    public static void saveLocationAndName (LinkedList<Task> tasks, String saveLocation, String fileName) throws DukeException {
        
        String strToAdd;
        String filePath = saveLocation + "/" + fileName;
        //Future: Check if filePath is valid?

        try {
            //Check if dir exists, creates if dir does not exist.
            File dataDir = new File(saveLocation);
            if (!dataDir.exists()) { new File("../data").mkdirs(); }
            //Future: Check if parent dir exists?

            //Check if file exists, creates if file does not exist.
            File saveFile =  new File(saveLocation + "/" + fileName);
            if (!saveFile.exists()) { saveFile.createNewFile(); }
            
            //Initialise FileWriter
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) { 
                strToAdd = task.toSaveString();
                fw.write(strToAdd);
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Save Failed: " + e.getMessage());
        }
    }
    
    public static LinkedList<Task> loadData (String saveLocation, String fileName) throws DukeException {
        LinkedList<Task> tasks = new LinkedList<>();
        
        File saveFile =  new File(saveLocation + "/" + fileName);
        try {
            //Read line by line
            Scanner s = new Scanner(saveFile); // create a Scanner using the File as the source
            String taskString;
            String[] taskArr;
            Task newTask;
            
            while (s.hasNext()) {
                taskString = (s.nextLine());
                
                //Splits string based on "@@@" delimiter
                taskArr = taskString.split("@@@");
                
                //Instantiates class using appropriate constructor
                switch(taskArr[0]) {
                case "TODO":
                    newTask = new Todo(taskArr[2]);
                    break;
                case "DEADLINE":
                    newTask = new Deadline(taskArr[2], taskArr[3]);
                    break; 
                case "EVENT":
                    newTask = new Event(taskArr[2], taskArr[3]);
                    break;
                default:
                    newTask = new Todo("Error Task");
                    break;
                }
                
                //Marks task as done if saveFile indicates that it is done
                if(taskArr[1].equals("true")) {
                    newTask.markAsDone();
                }
                //Append to tasks
                tasks.add(newTask);
            }
            
            return tasks;
        } catch (FileNotFoundException e) {
            //If file does not exist, throw DukeException File not Found
            throw new DukeException("Save File does not exist.");
        }
    }
}
