package execution;

import models.Deadline;
import models.Event;
import models.Task;
import models.ToDo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage object where the information will be stored in.
 */
public class Storage {

    private String filePath;

    /**
     * Constructor of a storage object, initiating the file we are storing the information into.
     *
     * @param filepath the string value of the filepath of the duke.txt file.
     */
    public Storage(String filepath) {
        this.filePath = filepath;
    }

    /**
     * This method will print the contents of the execution.Storage object (file).
     *
     * @throws FileNotFoundException if the file is not found based on the filepath.
     */
    public ArrayList<Task> readFileContents() throws FileNotFoundException {
        File currentFile = new File(this.filePath);
        Scanner sc = new Scanner(currentFile);
        ArrayList<Task> list = new ArrayList<>();
        while (sc.hasNext()) {
            String current = sc.nextLine();
            char type = current.charAt(0);
            int isDone = Integer.parseInt("" + current.charAt(4));
            String des = current.substring(8).trim();
            switch (type) {
                case 'T':
                    ToDo newT = new ToDo(isDone, des);
                    list.add(newT);
                    break;

                case 'D':
                    int index = des.indexOf('|');
                    String taskName = des.substring(0, index).trim();
                    String date = des.substring(index + 1).trim();
                    Deadline newD = new Deadline(isDone, taskName, date);
                    list.add(newD);
                    break;

                case 'E':
                    int index1 = des.indexOf('|');
                    String taskName1 = des.substring(0, index1).trim();
                    String date1 = des.substring(index1 + 1).trim();
                    Event newE = new Event(isDone, taskName1, date1);
                    list.add(newE);
                    break;

                default:
            }
        }
        return list;
    }

    /**
     * Checks if the file is empty. If it is empty, method will return true.
     *
     * @return a boolean on whether the file is empty or not.
     */
    public boolean isFileEmpty() {
        File file = new File(this.filePath);
        return 0 == file.length();
    }

    /**
     * Writes to the duke.txt file and saves the information there. This method will overwrite the information
     * on the current Duke.txt file.
     *
     * @param toAdd string pushed to the file.
     * @throws IOException if there is an issue writing to the file.
     */
    public void writeToFile(String toAdd) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        fw.write(toAdd);
        fw.close();
    }

    /**
     * Saves tasks into specified file.
     * @param taskList Tasks to be saved.
     */
    public void saveToDataFile(TaskList taskList) {
        try {
            ArrayList<Task> tasks = taskList.getList();
            FileWriter fileWriter = new FileWriter(filePath);
            assert fileWriter != null;
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Task task: tasks) {
                String taskData = task.toTextFile();
                bufferedWriter.write(taskData);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
