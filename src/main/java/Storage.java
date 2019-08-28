/**
 * This is a class to deal with writing and loading from hard disk.
 * @author Choong Yong Xin
 */


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        String currentDirectory = System.getProperty("user.dir");
        this.filePath = currentDirectory + filePath;
    }

    ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<Task>();
        File dataFile = new File(filePath);

        if (dataFile.exists()){
            Scanner fileReader = null; // create a Scanner using the File as the source
            try {
                fileReader = new Scanner(dataFile);
            } catch (FileNotFoundException e) {
                throw new FileNotFoundDukeException(filePath);
            }
            //Load the data from hard disk and create the taskList
            while (fileReader.hasNext()) {
                String readNewLine = fileReader.nextLine();
                String[] arrayInput = readNewLine.split(" \\| ");
                if (arrayInput[0].equals("T")) {
                    Todo newTodo = new Todo(arrayInput[2]);
                    taskList.add(newTodo);
                } else if (arrayInput[0].equals("D")) {
                    Deadline newDeadline = new Deadline(arrayInput[2], arrayInput[3]);
                    taskList.add(newDeadline);
                } else {
                    Event newEvent = new Event(arrayInput[2], arrayInput[3]);
                    taskList.add(newEvent);
                }
                //If tasks is done, mark as done.
                if (arrayInput[1].equals("\u2713")) {
                    taskList.get(taskList.size() - 1).markAsDone();
                }
            }
            fileReader.close();
            return taskList;
        } else {
            throw new FileNotFoundDukeException(filePath);
        }
    }


    //Function to append to file
    void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend + "\n");
        fw.close();
    }

    //Deletes specific line from file
    void deleteFromFile(String currentDirectory, String line) throws IOException {
        File inputFile = new File(currentDirectory + "/data/tasks.txt");
        File tempFile = new File(currentDirectory + "/data/temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(!trimmedLine.equals(line)) {
                writer.write(currentLine + System.getProperty("line.separator"));
            }
        }
        writer.close();
        reader.close();
        boolean isDeleted = inputFile.delete();
        boolean isRenamed = tempFile.renameTo(inputFile);
    }

    //Edits status of a record
    void editsFile(String currentDirectory, String line) throws IOException {
        File inputFile = new File(currentDirectory + "/data/tasks.txt");
        File tempFile = new File(currentDirectory + "/data/temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(!trimmedLine.equals(line)) {
                writer.write(currentLine + System.getProperty("line.separator"));
            } else {
                String newLine = "";
                String[] splittedParts = line.split("\\|");
                for (int j = 0; j < splittedParts.length; j++){
                    if (j != 0){
                        newLine += "|";
                    }
                    if (j == 1){
                        newLine += " \u2713 ";
                    }
                    else{
                        newLine += splittedParts[j];
                    }
                }
                writer.write(newLine + System.getProperty("line.separator"));
            }
        }
        writer.close();
        reader.close();
        boolean isDeleted = inputFile.delete();
        boolean isRenamed = tempFile.renameTo(inputFile);
    }
}
