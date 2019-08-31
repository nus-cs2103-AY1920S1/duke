package seedu.duke.storage;

import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class is used to read and write data from the text file.
 * filePath attribute stores the String of the absolute path of the text file.
 */
public class Storage {
    private String filepath;

    /**
     * Constructor for the Storage class.
     *
     * @param filepath Absolute filepath of the text file. Eg. "C:\\Users\\hatzi\\Documents\\Sourcetree\\duke\\data\\tasks.txt".
     */
    public Storage(String filepath){
        this.filepath = filepath;

    }

    /**
     *  Creates a text file at that location, if the text file does not exist at the specified location.
     *
     * @param filepath Absolute filepath of the text file.
     * @throws IOException An IOException may occur when trying to write the file.
     */
    public void createFile(String filepath) throws IOException{
        File f = new File(filepath);
        String data = "";

        // If the file does not exist, create sa new text file.
        if (f.exists() == false){
            try {
                Files.write(Paths.get(filepath), data.getBytes());
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Appends the String text to the text file.
     *
     * @param text The text to be appended to the txt file.
     * @throws IOException An IOException may occur when trying to write the file.
     */
    public void writeToFile(String text) throws IOException{
        FileWriter fw = new FileWriter(this.getFilePath(), true);
        fw.write(text + System.lineSeparator());
        fw.close();
    }

    /**
     * Clears and appends headers to the text file.
     *
     * @throws IOException An IOException may occur when trying to write the file.
     */
    public void clearFileBeforeSaving() throws IOException{
        // Overwrites text file and adds headers before saving tasks
        FileWriter fw = new FileWriter(this.getFilePath(), false);
        fw.write("event type | isDone | description | extra description" + System.lineSeparator());
        fw.close();
    }

    /**
     * Returns an ArrayList<Task> from the data loaded from the filePath.
     *
     * @return ArrayList<Task> parsed from text file.
     * @throws FileNotFoundException An FilenotFoundException may occur when if filePath is invalid.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        // Initialises variables to handle the txt input file.
        ArrayList<String> inputsFromFile = new ArrayList<>();
        String description = ""; String extraDescription = "";
        ArrayList<Task> tasks = new ArrayList<>();

        // Creates a scanner object to read the txt file from filePath.
        Scanner scanner = new Scanner(new File(getFilePath()));

        while (scanner.hasNextLine()){
            inputsFromFile.add(scanner.nextLine());
        }

        for (String input: inputsFromFile){
            // A Possible input string is: "D | 0 | CS2103 Ip  | Wed 2359".

            String[] words = input.split("\\|") ;
            Boolean isDone = false;

            if (words[0].length() < 3 ){
                // If condition to avoid reading in the header.

                if (words[0].contains("T")) {
                    // Create a Todo class.

                    if (words[1].contains("1")) {
                        isDone = true;
                    } else if (words[1].contains("0")) {
                        isDone = false;
                    }
                    description = words[2].trim();

                    Todo newTodo = new Todo(description, isDone);
                    tasks.add(newTodo);

                } else if (words[0].contains("E")) {
                    // Create an Event class.

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
                    // Create a Deadline class.

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

    /**
     * Returns the filePath attribute.
     *
     * @return String filePath attribute.
     */
    public String getFilePath(){
        return this.filepath;
    }
}
