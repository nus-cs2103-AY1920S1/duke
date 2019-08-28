import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWriterClass {

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

}