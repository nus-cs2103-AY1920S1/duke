import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TaskFileReader {
    public void loadTaskContents(String filePath, ArrayList<Task> myList)
            throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String[] output = s.nextLine().split(" \\| ");
            boolean isDone;
            if (output[1].equals("Done")) {
                isDone = true;
            }
            else {
                isDone = false;
            }
            String date = "";
            String description = output[2];
            if (output.length > 3) {
                date = output[3];
            }
            switch (output[0]) {
            case "T":
                myList.add(new ToDo(description));
                break;
            case "D":
                myList.add(new Deadline(description, date, isDone));
                break;
            case "E":
                myList.add(new Event(description, date, isDone));
                break;
            }
        }
    }
}
