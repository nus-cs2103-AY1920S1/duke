import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is a class to deal with writing and loading from hard disk.
 * @author Choong Yong Xin
 */

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = System.getProperty("user.dir") + filePath;
    }

    /**
     * Returns an ArrayList containing the tasks loaded from hard disk.
     *
     * @return ArrayList containing the tasks.
     * @throws DukeException if file not found.
     */
    ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<Task>();
        File dataFile = new File(filePath);

        if (dataFile.exists()) {
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
                if (arrayInput[1].equals("1")) {
                    taskList.get(taskList.size() - 1).markAsDone();
                }
            }
            fileReader.close();
            return taskList;
        } else {
            throw new FileNotFoundDukeException(filePath);
        }
    }


    /**
     * Appends string to file.
     *
     * @param filePath String representing the filepath to append to.
     * @param textToAppend String to append to file.
     */
    void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend + "\n");
        fw.close();
    }

    /**
     * Deletes specific line from file.
     *
     * @param currentDirectory current directory.
     * @param line String to delete to file.
     */
    void deleteFromFile(String currentDirectory, String line) throws IOException {
        File inputFile = new File(currentDirectory + "/data/tasks.txt");
        File tempFile = new File(currentDirectory + "/data/temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if (!trimmedLine.equals(line)) {
                writer.write(currentLine + System.getProperty("line.separator"));
            }
        }
        writer.close();
        reader.close();
        boolean isDeleted = inputFile.delete();
        assert isDeleted : "tempFile should be deleted";
        boolean isRenamed = tempFile.renameTo(inputFile);
        assert isRenamed : "file should be renamed";
    }

    /**
     * Edits specific line from file.
     *
     * @param currentDirectory current directory.
     * @param line String to edit in file.
     */
    void editsFile(String currentDirectory, String line) throws IOException {
        File inputFile = new File(currentDirectory + "/data/tasks.txt");
        File tempFile = new File(currentDirectory + "/data/temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if (!trimmedLine.equals(line)) {
                writer.write(currentLine + System.getProperty("line.separator"));
            } else {
                String newLine = "";
                String[] splittedParts = line.split("\\|");
                for (int j = 0; j < splittedParts.length; j++) {
                    if (j != 0) {
                        newLine += "|";
                    }
                    if (j == 1) {
                        newLine += " 1 ";
                    } else {
                        newLine += splittedParts[j];
                    }
                }
                writer.write(newLine + System.getProperty("line.separator"));
            }
        }
        writer.close();
        reader.close();
        boolean isDeleted = inputFile.delete();
        assert isDeleted : "tempFile should be deleted";
        boolean isRenamed = tempFile.renameTo(inputFile);
        assert isRenamed : "file should be renamed";
    }
}
