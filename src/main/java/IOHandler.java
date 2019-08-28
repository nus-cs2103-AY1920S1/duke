import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class IOHandler {
    void addToLocalSave(Task task) throws IOException {
        PrintWriter printWriter = null;
        Parser parser = new Parser();

        String fileName = "C:\\Users\\NINGS\\OneDrive\\Documents\\duke\\data\\duketasks.txt";
        FileWriter fileWriter = new FileWriter(fileName, true);
        printWriter = new PrintWriter(fileWriter);
        printWriter.println(parser.writeTaskAsText(task));

        printWriter.close();
    }
}
