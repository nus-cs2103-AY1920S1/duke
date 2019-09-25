package main;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the storing of the TaskList to a file on the hard disk.
 *
 * This allows the list of Tasks to be stored and accessed for subsequent initialisations of Duke.
 */
public class Storage {
    private Path path = Paths.get(System.getProperty("user.dir"));
    private File data = new File(path + "/data/duke.txt");

    public Storage() { }

    /**
     * Returns an ArrayList of tasks from the data file on the hard disk.
     *
     * This method is invoked whenever an instance of Duke is created. It reads from the data/duke.txt file. Should the
     * file not be found, a FileNotFoundException is thrown and caught. A parser is also implemented in this method to
     * read from the data file.
     *
     * @return An ArrayList of Tasks from the stored data file.
     */
    public ArrayList<Task> readFromFile() {
        ArrayList<Task> toReturn = new ArrayList<>();

        try {
            Scanner sc = new Scanner(data);

            while (sc.hasNextLine()) {
                String[] next = sc.nextLine().split("-");

                switch (next[0]) {
                case ("T"):
                    Task todo = new Todo(next[2]);

                    if (Integer.valueOf(next[1]) == 1) {
                        todo.setDone();
                    }
                    toReturn.add(todo);
                    break;


                case ("E"):
                    Task event = new Event(next[2], next[3]);

                    if (Integer.valueOf(next[1]) == 1) {
                        event.setDone();
                    }
                    toReturn.add(event);
                    break;

                case ("D"):
                    Task deadline = new Deadline(next[2], next[3]);

                    if (Integer.valueOf(next[1]) == 1) {
                        deadline.setDone();
                    }
                    toReturn.add(deadline);
                    break;

                default:
                    break;
                }
            }
            sc.close();
        } catch (FileNotFoundException fileExp) {
            System.out.println(fileExp);
        }

        return toReturn;
    }

    /**
     * Writes all the tasks in a given ArrayList into the data/duke.txt file.
     *
     * A StringBuilder is used to concatenate all the strings from the toFile() method of each Task. This is then
     * stored into the data file.
     *
     * @param list ArrayList of all the Tasks to be written into the data file.
     */
    public void writeToFile(ArrayList<Task> list) {
        try {
            Path path = Paths.get(System.getProperty("user.dir"));
            File data = new File(path + "/data/duke.txt");
            FileWriter fw = new FileWriter(data);

            StringBuilder toWrite = new StringBuilder("");
            for(Task task : list) {
                toWrite.append(task.toFile() + " \n");
            }

            fw.write(toWrite.toString());
            fw.close();
        } catch (IOException IOe) {
            System.err.println(IOe);
        }
    }

}
