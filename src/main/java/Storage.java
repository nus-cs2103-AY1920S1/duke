import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Deals with storage - loading tasks from file and saving to file
 */
public class Storage {
    private File file;

    private String filePath;

    public Storage(String filePath) {
        this.file = new File(filePath);
        this.filePath = filePath;
    }

    public void printFileContents() throws FileNotFoundException {
        // if file is empty, inform the user
        if (file.exists() && file.length() == 0) {
            System.out.println("Your task list is empty. Use Duke to " +
                    "add tasks to your list!");
        } else {
            // read file using Scanner class
            Scanner s = new Scanner(this.file);
            // print file contents line by line until end of file
            System.out.println("Here is your current task list: ");
            while (s.hasNext()) {
                System.out.println(s.nextLine());
            }
        }
    }

    public void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        fw.write(textToAdd);
        fw.write("\n"); // add newline
        fw.close();
    }
}

