import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

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

    public static void readSavedList(TaskList tasklist) {
        try {
            readFileContents("DukeList.txt", tasklist);
        } catch (FileNotFoundException e) {
        }
    }
}
