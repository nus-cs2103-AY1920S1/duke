package components;

import commands.DukeException;
import tasks.Task;


import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filepath;
    public Storage(String filepath) {
        this.filepath = filepath;
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
     *  and the ArrayList of existing Tasks otherwise.
     */
    public ArrayList<Task> load() throws DukeException {
        try (FileInputStream fi = new FileInputStream(new File(filepath));
             ObjectInputStream oi = new ObjectInputStream(fi)) {

             //TODO: Check if filepath exists, if not create it

            ArrayList<Task> tasks = new ArrayList<>();
            //numObjects refers to how many Tasks were stored in this file.
            int numObjects = oi.readInt();
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
