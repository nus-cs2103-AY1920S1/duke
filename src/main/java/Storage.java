import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage { private String filepath;

    public Storage(String filepath){
        this.filepath = filepath;

    }

    public void createFile(String filepath) throws IOException{
        File f = new File(filepath);
        String data = "";

        // If the file does not exist, create a new text file
        if (f.exists() == false){
            try {
                // Need to create a new empty text file as file doesn't exist
                Files.write(Paths.get(filepath), data.getBytes());
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void writeToFile(String text) throws IOException{
        // appends the string to the text file as specified in filepath
        FileWriter fw = new FileWriter(filepath, true);
        fw.write(text + System.lineSeparator());
        fw.close();
    }

    public void clearFileBeforeSaving() throws IOException{
        // Overwrites text file and adds headers before saving tasks
        FileWriter fw = new FileWriter(filepath, false);
        fw.write("event type | isDone | description | extra description" + System.lineSeparator());
        fw.close();
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        // Loads the tasks from the filepath into an ArrayList of Tasks

        // Initialises variables to handle the txt input file
        ArrayList<String> inputsFromFile = new ArrayList<>();
        String description = ""; String extraDescription = "";
        ArrayList<Task> tasks = new ArrayList<>();

        // Creates a scanner object to read the txt file from filePath
        Scanner scanner = new Scanner(new File(this.filepath));

        while (scanner.hasNextLine()){
            inputsFromFile.add(scanner.nextLine());
        }
        for (String input: inputsFromFile){
            // possible input string: "D | 0 | CS2103 Ip  | Wed 2359"
            String[] words = input.split("\\|") ;
            Boolean isDone = false;

            if (words[0].length() < 3 ){

                if (words[0].contains("T")) { // Will avoid header
                    // Create a Todo class

                    if (words[1].contains("1")) {
                        isDone = true;
                    } else if (words[1].contains("0")) {
                        isDone = false;
                    }
                    description = words[2].trim();

                    Todo newTodo = new Todo(description, isDone);
                    tasks.add(newTodo);

                } else if (words[0].contains("E")) {
                    // Create an Event class

                    if (words[1].contains("1")) {
                        isDone = true;
                    } else if (words[1].contains("0")) {
                        isDone = false;
                    }
                    description = words[2].trim();
                    extraDescription = words[3].trim();

                    Event newEvent = new Event(description, extraDescription, isDone);
                    tasks.add(newEvent);

                } else if (words[0].contains("D")) {
                    // Create a Deadline class

                    if (words[1].contains("1")) {
                        isDone = true;
                    } else if (words[1].contains("0")) {
                        isDone = false;
                    }
                    description = words[2].trim();
                    extraDescription = words[3].trim();

                    Deadline newDeadline = new Deadline(description, extraDescription, isDone);
                    tasks.add(newDeadline);
                }
            }
        }
        return tasks;

    }
}
