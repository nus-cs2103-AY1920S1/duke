import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class IOHandler {
    private String saveFileName;

    public IOHandler() {
        saveFileName = "C:\\Users\\NINGS\\OneDrive\\Documents\\duke\\data\\duketasks.txt";
    }

    void addToLocalSave(Task task) throws IOException {
        PrintWriter printWriter = null;
        Parser parser = new Parser();

        FileWriter fileWriter = new FileWriter(saveFileName, true);
        printWriter = new PrintWriter(fileWriter);
        printWriter.println(parser.writeTaskAsText(task));

        printWriter.close();
    }

    void overwriteLocalSave(ArrayList<Task> listOfTasks) throws IOException {
        PrintWriter printWriter = null;
        Parser parser = new Parser();

        FileWriter fileWriter = new FileWriter(saveFileName, false);
        printWriter = new PrintWriter(fileWriter);
        
        for (Task task : listOfTasks) {
            printWriter.println(parser.writeTaskAsText(task));
        }

        printWriter.close();
    }
}
