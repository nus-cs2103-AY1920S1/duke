package duke.storage;

import duke.DukeException;
import duke.parser.Task;
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
    private UiText ui = new UiText();

    /**
     * Constructor to construct a Storage with given file path.
     * @param filePath givenFile path to retrive anf storage the tasks.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Print what's the content in the file.
     *
     * @throws FileNotFoundException throw exception if the file does not exist
     */
    public void printFileContents() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            ui.printlnMsg(sc.nextLine());
        }
    }

    /**
     * convert the task in the file to arraylist;
     * @return ArrayList of Task
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> arr = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                String[] input = sCurrentLine.split(" \\| ");
                arr.add(new Task(input));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

//    public static void writeToFile(String filePath, String text) throws IOException {
//        FileWriter fw = new FileWriter(filePath);
//        fw.write(text);
//        fw.close();
//    }


    /**
     * write new task to the existing fille without overwritting the content of the file.
     * @param text task to converted to String to append to the file.
     * @throws IOException
     */
    public void appendToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        fw.write(text);
        fw.close();
    }

    /**
     * Update the file when is a delete or done command.
     * @param tasks updated arraylist of Task.
     * @throws IOException
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


            }
            writer.write(input);
        }
        writer.close();
    }
}
