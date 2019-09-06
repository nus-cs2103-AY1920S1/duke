/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String file;

    public Storage(String file) {
        this.file = file;
    }

    /**
     * Method that reads the designated file and stores the content into an arraylist.
     * Is called upon when Duke initialises.
     * @return Returns an arraylist of tasks written in the file given the file path.
     */
    public ArrayList<Task> load()  {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File f = new File(file);
            Scanner sc = new Scanner(f);
            Task t;
            while (sc.hasNext()) {
                String next = sc.nextLine();
                String[] arr = next.split("@");
                boolean isDone = arr[1].contains("1");
                String description = arr[2];
                if (arr[0].contains("T")) {
                    t = new Todos(description.trim(), isDone);
                } else if (arr[0].contains("D")) {
                    t = new Deadline(description.trim(), isDone, arr[3].trim());
                } else {
                    t = new Event(description.trim(), isDone, arr[3].trim());
                }

                list.add(t);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (ParseException e) {
            System.out.println("Date in wrong format");
        }
        return list;
    }

    /**
     * Rewrites the file with the new arraylist of tasks.
     * @param list arraylist of tasks that are updated after every command given to Duke.
     */
    public void writeFile(ArrayList<Task> list) {
        try {
            FileWriter fw = new FileWriter(file);
            String textToAdd = "";
            for (Task task : list) {
                textToAdd += task.print() + "\n";
            }
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }
}
