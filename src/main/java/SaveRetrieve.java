import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SaveRetrieve {
    private String fPath;
    public SaveRetrieve(String fpath) {
        this.fPath = fpath;
    }

    public static void writeOnFile(String fpath, String ftext) throws IOException, DukeException {
        FileWriter fwrit = new FileWriter(fpath); //Creates a FileWriter object for the given file path
        fwrit.write(ftext);
        fwrit.close();
    }

    public static void getFileText(String fpath, ChatLike cl) throws FileNotFoundException, DukeException {
        File f = new File(fpath); // create a File for the given file path
        Scanner S = new Scanner(f); // create a Scanner using the File as the source
        while (S.hasNext()) {
            String temp = S.nextLine();
            cl.readFromFile(temp);
        }
    }
}
