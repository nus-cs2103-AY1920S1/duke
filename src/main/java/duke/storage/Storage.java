package duke.storage;

import duke.task.Task;
import duke.ui.UiText;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;


/**
 * Loading task from the file and save task to the file.
 * @author Yang Shuting
 */

public class Storage {
    private static File file;
    public static final String DEFAULT_PATH = "src" + UiText.fileSeparator + "main" +
            UiText.fileSeparator + "data" + UiText.fileSeparator + "duke.txt";

    /**
     * Constructor to construct a Storage with given file path.
     * @param filePath givenFile path to retrive anf storage the tasks.
     */
    public Storage(String filePath) {
        File tempFile = new File(filePath);
        if (tempFile.exists()){
            this.file = tempFile;
        } else {
            File dir = new File("data");
            if (!dir.exists()) {
                try {
                    dir.mkdir();
                } catch (SecurityException se) {
                    //do something
                }
            }
            assert (dir.exists());
            file = new File("data", "duke.txt");
        }

    }

    /**
     * Print what's the content in the file.
     *
     * @throws FileNotFoundException throw exception if the file does not exist
     */
    public static String fileContents() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        return UiText.itemsFromFile(sc);
    }

    /**
     * convert the task in the file to arraylist.
     * @return ArrayList of Task
     */
    public ArrayList<Task> load() {
        ArrayList<Task> arr = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file)))  {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] input = currentLine.split(" \\| ");
                arr.add(new Task(input));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

    /**
     * write new task to the existing fille without overwritting the content of the file.
     * @param text task to converted to String to append to the file.
     * @throws IOException file not found
     */
    public void appendToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        fw.write(text);
        fw.close();
    }

    /**
     * Update the file when is a delete or done command.
     * @param tasks updated arraylist of Task.
     * @throws IOException file not found
     */
    public void updateFile(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(file);
        for (Task task : tasks) {
            String input = "";
            switch (task.getTaskType()) {
            case TODO:
                input = String.format("T | %d | %s\n",
                        task.getStatusBit(),
                        task.getDescription());
                break;

            case DEADLINE:
                input = String.format("D | %d | %s | %s\n",
                        task.getStatusBit(),
                        task.getDescription(),
                        task.getInformation());
                break;
            case EVENT:
                input = String.format("E | %d | %s | %s\n",
                        task.getStatusBit(),
                        task.getDescription(),
                        task.getInformation());
                break;
            default:
                input = "";
            }
            writer.write(input);
        }
        writer.close();
    }
}
