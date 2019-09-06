package duke.logic;

import duke.task.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage space that stores a file path.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Rewrites file data with text to be added.
     *
     * @param filePath the representation of the path of the file.
     * @param textToAdd text to be added to file.
     * @throws IOException when I/O operations fail.
     */
    private void rewriteFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Appends text to be added to the end of the file data.
     *
     * @param filePath the representation of the path of the file.
     * @param textToAppend text to be appended to file.
     * @throws IOException when I/O operations fail.
     */
    private void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Rewrites the file data with new data from an ArrayList<Task> </Task>.
     *
     * @param list the ArrayList<Task> </Task> to be used.
     */
    public void write(ArrayList<Task> list) {
        //Store all the latest data from the ArrayList
        try {
            rewriteFile(this.filePath, "");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        for(Task task : list) {
            StringBuilder temp = new StringBuilder();
            temp.append(task.getTaskType());
            temp.append(" | ");
            if(task.isDone()) {
                temp.append(1);
            } else {
                temp.append(0);
            }
            temp.append(" | ");
            temp.append(task.getDescription());
            if((task instanceof Event) || (task instanceof Deadline)) {
                temp.append(" | ");
                if(task instanceof Event) {
                    temp.append(((Event) task).getExactDuration());
                } else {
                    temp.append(((Deadline) task).getExactBy());
                }
            }
            try {
                appendToFile(this.filePath, temp.toString() + System.lineSeparator());
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    /**
     * Loads data from a file into an ArrayList<Task> </Task>.
     *
     * @return an ArrayList<Task> </Task> with data from file.
     * @throws FileNotFoundException If file cannot be found.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> list = new ArrayList<>();
        File f = new File(this.filePath);
        Scanner s = new Scanner(f);
        while(s.hasNext()) {
            String[] temp = s.nextLine().split(" ");
            String task = (String) Array.get(temp, 0);
            for(int i = 0; i < temp.length; i++) {
                System.out.println((String)Array.get(temp,i));
            }
            Task newTask;
            if(task.equals("T")) {
                newTask = new Todo((String)Array.get(temp,4));
            } else if(task.equals("D")) {
                newTask = new Deadline((String)Array.get(temp,4),
                        (String)Array.get(temp, 6) + " " +
                                (String)Array.get(temp, 7));
            } else {
                newTask = new Event((String)Array.get(temp,4),
                        (String)Array.get(temp, 6) + " " +
                                (String)Array.get(temp, 7));
            }
            if(((String)Array.get(temp, 2)).equals("1")) {
                newTask.setDone();
            }
            list.add(newTask);
        }
        return list;
    }
}
