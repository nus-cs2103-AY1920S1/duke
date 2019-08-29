package misc;

import task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void addToLocalSave(Task task) throws IOException {
        Parser parser = new Parser();
        String saveFileName = filePath + File.separator + "duketasks.txt";

        FileWriter fileWriter = new FileWriter(saveFileName, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(parser.writeTaskAsText(task));

        printWriter.close();
    }

    public void overwriteLocalSave(ArrayList<Task> listOfTasks) throws IOException {
        Parser parser = new Parser();
        String saveFileName = filePath + File.separator + "duketasks.txt";

        FileWriter fileWriter = new FileWriter(saveFileName, false);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        
        for (Task task : listOfTasks) {
            printWriter.println(parser.writeTaskAsText(task));
        }

        printWriter.close();
    }

    public ArrayList<Task> readSaveFile() throws IOException {
        String saveFileName = filePath + File.separator + "duketasks.txt";
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
        }

        return listOfExistingTasks;
    }
}
