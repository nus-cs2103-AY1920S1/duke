package components;

import tasks.Task;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Overwrites entire file with current items in Arr, using '***' as a separator.
     */
    public void save(ArrayList<Task> arr) {
        //First delete the old file
        File file = new File(filepath);
        if (file.exists()) file.delete();

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
            System.err.println("I could not save your updated list to my file :( Please try again.");
        }
    }

    /**
     * Returns an arraylist of Tasks.
     *
     * @return an empty ArrayList if file has not been created or is empty, and the ArrayList of existing Tasks otherwise.
     */
    public ArrayList<Task> load() {
        try (FileInputStream fi = new FileInputStream(new File(filepath));
             ObjectInputStream oi = new ObjectInputStream(fi)) {
            ArrayList<Task> tasks = new ArrayList<>();
            //numObjects refers to how many Tasks were stored in this file.
            int numObjects = oi.readInt();
            for (int i = 0; i < numObjects; i++) {
                Task t = (Task) oi.readObject();
                tasks.add(t);
            }
            return tasks;

        } catch (FileNotFoundException e) {
            Ui.printErr("I could not find the file I saved your data to! I'll load an empty list first.");
            return new ArrayList<>();
        } catch (IOException e) {
            Ui.printErr("I had an issue reading your items from memory! I'll load an empty list first.");
            return new ArrayList<>();
        } catch (ClassNotFoundException e) {
            Ui.printErr("Your data does not resemble any format I know. I'll load an empty list first.");
            return new ArrayList<>();
        }
    }

}
