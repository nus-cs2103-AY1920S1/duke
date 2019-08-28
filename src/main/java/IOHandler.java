import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;

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

    ArrayList<Task> readSaveFile() throws IOException {
        File saveFile = new File(saveFileName);
        ArrayList<Task> listOfExistingTasks = new ArrayList<>();

        if (saveFile.exists()) {
            Scanner sc = new Scanner(saveFile);
            Parser parser = new Parser();

            while (sc.hasNextLine()) {
                String stringTask = sc.nextLine();
                Task task = parser.readTextAsTask(stringTask);
                listOfExistingTasks.add(task);
            }

            sc.close();
        } else {
            saveFile.createNewFile();
        }

        return listOfExistingTasks;
    }
}
