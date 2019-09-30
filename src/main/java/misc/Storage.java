package misc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;

import task.Task;

/**
 * Represents a Storage object that loads or saves user tasks upon program
 * start up or exit.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        File folder = new File(filePath);

        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    /**
     * Saves and updates user tasks by adding on to a local save file when a new task is added.
     * A new local save file is created if it does not already exists.
     * @param task The Task to be saved.
     * @throws IOException if the task cannot be saved.
     */
    public void addToLocalSave(Task task) throws IOException {
        Parser parser = new Parser();
        String saveFileName = filePath + File.separator + "duketasks.txt";

        FileWriter fileWriter = new FileWriter(saveFileName, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(parser.writeTaskAsText(task));

        printWriter.close();
    }

    /**
     * Saves and updates user tasks by overwriting all contents on a local save file.
     * This occurs when an existing task is deleted from the user task list.
     * @param listOfTasks The ArrayList that contains all of user tasks to be saved.
     * @throws IOException if any of the tasks cannot be saved.
     */
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

    /**
     * Reads from an existing save file to retrieve the user tasks that remained after the 
     * program last exitted.
     * @return an ArrayList of leftover tasks from last session.
     * @throws IOException if any of the task cannot be read.
     */
    public ArrayList<Task> readSaveFile() throws IOException {
        String saveFileName = filePath + File.separator + "duketasks.txt";
        File saveFile = new File(saveFileName);
        
        ArrayList<Task> listOfExistingTasks = new ArrayList<>();

        if (saveFile.exists()) {
            Scanner sc = new Scanner(saveFile);
            Parser parser = new Parser();

            readTasksIntoTaskList(listOfExistingTasks, sc, parser);
            sc.close();
        }

        return listOfExistingTasks;
    }

    private void readTasksIntoTaskList(ArrayList<Task> listOfExistingTasks, Scanner sc, Parser parser) {
        while (sc.hasNextLine()) {
            String stringTask = sc.nextLine();
            Task task = parser.readTextAsTask(stringTask);
            listOfExistingTasks.add(task);
        }
    }
}
