package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Saves file to the specified filePath.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage.
     * @param filePath a String that represents the filepath to save this program's output
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the saved file and adds the tasks to TaskList.
     * @return the list of tasks parsed from the saved file
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> list = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        return readFile(list, s);
    }

    /**
     * Updates the file in the corresponding filePath.
     * @param arr an ArrayList of Tasks to update the file in the corresponding filePath
     */
    public static  void  updateFile(ArrayList<Task> arr) {
//        File dir = new File("/Users/joannasara/Desktop/duke/data");
        Path path = FileSystems.getDefault().getPath(".");
        File dir = new File(path.toString() + "/data");
        if (!dir.exists()) {
            dir.mkdir();
        }

        final StringBuilder sb = new StringBuilder();
        arr.stream()
                .forEach(task -> sb.append(task.getFileStringFormat() + "\n"));

        try {
            File f = new File("data/tasks.txt");
            f.createNewFile();
            FileWriter fw = new FileWriter("data/tasks.txt");
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Add the current arraylist of tasks to archive.txt
     * @param arr an ArrayList of Tasks
     */
    public static void addToArchive(ArrayList<Task> arr) {
//        File dir = new File("/Users/joannasara/Desktop/duke/data");
        Path path = FileSystems.getDefault().getPath(".");
        File dir = new File(path.toString() + "/data");        if (!dir.exists()) {
            dir.mkdir();
        }

        final StringBuilder sb = new StringBuilder();
        arr.stream()
                .forEach(task -> sb.append(task.getFileStringFormat() + "\n"));

        try {
            File f = new File("data/archive.txt");
            FileWriter fw = new FileWriter(f, true);
            fw.write(sb.toString());
            fw.close();

            File currentFile = new File("data/tasks.txt");
            currentFile.createNewFile();
            FileWriter fileWriter = new FileWriter(currentFile);
            fileWriter.write("");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Loads the ArrayList of Tasks saved in archive.txt
     * @return an ArrayList containing the Tasks saved in archive.txt
     * @throws FileNotFoundException
     */
    public static ArrayList<Task> loadArchive() throws FileNotFoundException{
        ArrayList<Task> list = new ArrayList<>();
        File f = new File("data/archive.txt");
        Scanner s = new Scanner(f);
        return readFile(list, s);
    }

    /**
     * Read the tasks from the given Scanner and saved it in list
     * @param list an ArrayList of Tasks to save the read Tasks
     * @param s Scanner for reading the Tasks
     * @return an ArrayList of Tasks
     */
    private static ArrayList<Task> readFile(ArrayList<Task> list, Scanner s) {
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] arr = line.split("[|]");
            boolean isDone = !arr[1].trim().equals("0");
            Task task;
            if (arr[0].trim().equals("T")) {
                task = new Todo(arr[2].trim(), isDone, "");
            } else if (arr[0].trim().equals("D")) {
                task = new Deadline(arr[2].trim(), isDone, arr[3].trim());
            } else {
                task = new Event(arr[2].trim(), isDone, arr[3].trim());
            }
            list.add(task);
        }
        return list;
    }
}
