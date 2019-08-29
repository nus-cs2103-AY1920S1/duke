import java.io.FileWriter;
import java.io.IOException;

public class FileWriterClass {
    public static void writeToFile(String filePath, String textToAdd){
        try {
            try {
                FileWriter fw = new FileWriter(filePath);
                System.out.println("written in " + filePath);
                fw.write(textToAdd);
                fw.close();
            } catch (IOException e) {
                throw new DukeException((new Border()) + "\n     ☹ Something went wrong: " + e.getMessage() + "\n" + (new Border()));
            }
        } catch (DukeException e){
            System.out.println(e.getMessage());
        }
    }
}
