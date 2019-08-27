import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    /**
     * Writes the tasklist to the file.
     * @param filePath Location of the file to write to.
     * @param textToAdd What is written to the file.
     * @throws IOException
     */
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Saves the current task list into the specified txt file.
     * @param tasklist current list of tasks to be saved.
     */
    public static void save(TaskList tasklist) {
        Task[] arr = tasklist.getTasks();
        String file2 = "DukeList.txt";
        StringBuilder sb = new StringBuilder();
        if (arr[0] != null) {
            int x = 0;
            while (arr[x + 1] != null) {
                sb.append(arr[x].toFormattedString());
                sb.append("\n");
                x++;
            }
            sb.append(arr[x].toFormattedString());
        } else {}
        try {
            writeToFile(file2, sb.toString());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * read the saved tasks in the file and update the tasklist.
     * @param filePath location of file to be read.
     * @param tasklist tasklist to be updated with the tasks read in the file.
     * @throws FileNotFoundException If the saved file does not exist.
     */
    public static void readFileContents(String filePath, TaskList tasklist) throws FileNotFoundException {
        try {
            Task[] tasks = tasklist.getTasks();
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            int x = 0;
            while (s.hasNext()) {
                String currtask = s.nextLine();
                tasks[x] = Task.readString(currtask);
                x++;
            }
        } catch (UnknownInputException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * reads the tasks in the txt file and updates the tasklist.
     * @param tasklist tasklist that will be updated with the saved tasks.
     */
    public static void readSavedList(TaskList tasklist) {
        try {
            readFileContents("DukeList.txt", tasklist);
        } catch (FileNotFoundException e) {
        }
    }
}
