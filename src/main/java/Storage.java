import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    public static String DATA_FOLDER_PATH = "../data";
    public static String DUKE_TXT_PATH = "../data/duke.txt";

    public static void makeDirectory (String filePath) throws IOException {
        File file = new File(filePath);
        file.mkdirs();
    }

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void retrieve () throws FileNotFoundException {
        File f = new File(Storage.DUKE_TXT_PATH); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String text = s.nextLine();

            String[] split = text.split(" \\| ");

            String description = split[2];

            Task t = null;

            switch (split[0]) {
            case "T":
                t = new Todo(description);
                break;
            case "D":
                t = new Deadline(description, split[3]);
                break;
            case "E":
                t = new Event(description, split[3]);
                break;
            }

            boolean isDone = split[1].equals("1");

            if (t != null) {
                t.isDone = isDone;
                list.add(t);
            }
        }
    }

}