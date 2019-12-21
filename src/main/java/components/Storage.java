package components;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import commands.DukeException;
import tasks.Task;

public class Storage {
    private String filepath;

    /**
     * Create a storage and the file to save to.
     *
     * @param filepath path to save to.
     * @throws DukeException if file cannot be created or accessed.
     */
    public Storage(String filepath) throws DukeException {
        this.filepath = filepath;
        File file = new File(filepath);
        if (!file.exists()) {
            try {
                file.createNewFile();
                try (FileOutputStream fout = new FileOutputStream(filepath);
                     ObjectOutputStream oos = new ObjectOutputStream(fout)) {
                    //Write a 0 in
                    oos.writeInt(0);
                } catch (IOException e) {
                    throw new DukeException("I could not initialise a new file to save your tasks :(");
                }
            } catch (IOException e) {
                throw new DukeException("I could not initialise a new file to save your tasks :(");
            }


        }
    }

    /**
     * Overwrites entire file with current items.
     */
    public void save(ArrayList<Task> arr) throws DukeException {
        //First delete the old file
        File file = new File(filepath);
        if (file.exists()) {
            file.delete();
        }

        //Write the objects to file
        try (FileOutputStream fout = new FileOutputStream(filepath);
             ObjectOutputStream oos = new ObjectOutputStream(fout)) {
            //First piece of data is how many objects there are
            oos.writeInt(arr.size());
            //Subsequently, all the tasks are written in
            for (Task task : arr) {
                oos.writeObject(task);
            }
        } catch (IOException e) {
            throw new DukeException("I could not save your updated list to my file :( Please try again.");
        }
    }

    /**
     * Returns an arraylist of Tasks.
     *
     * @return an empty ArrayList if file has not been created or is empty,
     *     and the ArrayList of existing Tasks otherwise.
     */
    public ArrayList<Task> load() throws DukeException {
        try (FileInputStream fi = new FileInputStream(new File(filepath));
             ObjectInputStream oi = new ObjectInputStream(fi)) {
            ArrayList<Task> tasks = new ArrayList<>();
            //numObjects refers to how many Tasks were stored in this file.
            int numObjects = 0;
            try {
                numObjects = oi.readInt();
            } catch (EOFException e) {
                throw new DukeException("I found an empty file! I'll load an empty list first.");
            }
            for (int i = 0; i < numObjects; i++) {
                Task t = (Task) oi.readObject();
                tasks.add(t);
            }
            return tasks;

        } catch (FileNotFoundException e) {
            throw new DukeException("I didn't detect any file with tasks in it! I'll load an empty list first.");
        } catch (IOException e) {
            throw new DukeException("I had an issue reading your items from memory! I'll load an empty list first.");
        } catch (ClassNotFoundException e) {
            throw new DukeException("Your data does not resemble any format I know. I'll load an empty list first.");
        }
    }

}
