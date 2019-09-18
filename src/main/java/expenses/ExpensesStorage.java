package expenses;

import commands.DukeException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ExpensesStorage {
    private String filepath;

    public ExpensesStorage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Overwrites entire file with current items
     */
    public void save(ArrayList<Item> credit, ArrayList<Item> debit) throws DukeException {
        //First delete the old file
        File file = new File(filepath);
        if (file.exists()) {
            file.delete();
        }

        try (FileOutputStream fout = new FileOutputStream(filepath);
             ObjectOutputStream oos = new ObjectOutputStream(fout)) {
            oos.writeInt(credit.size());
            for (Item i : credit) {
                oos.writeObject(i);
            }
            oos.writeInt(debit.size());
            for (Item i : debit) {
                oos.writeObject(i);
            }

        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            throw new DukeException("I could not save your updated list to my file :( Please try again.");
        }
    }

    /**
     * Returns an arraylist of arraylist of Items. The first list is the credit, the second is the debit.
     *
     * @return an empty ArrayList if file has not been created or is empty,
     *     and the ArrayList of existing Items otherwise.
     */
    ArrayList<ArrayList<Item>> load() throws DukeException {
        try (FileInputStream fi = new FileInputStream(new File(filepath));
             ObjectInputStream oi = new ObjectInputStream(fi)) {
            ArrayList<ArrayList<Item>> lists = new ArrayList<>();
            ArrayList<Item> credits = new ArrayList<>();
            int creditItems = oi.readInt();
            for (int i = 0; i < creditItems; i++) {
                credits.add((Item) oi.readObject());
            }
            ArrayList<Item> debits = new ArrayList<>();
            int debitItems = oi.readInt();
            for (int i = 0; i < debitItems; i++) {
                debits.add((Item) oi.readObject());
            }
            lists.add(credits);
            lists.add(debits);
            return lists;

        } catch (FileNotFoundException e) {
            throw new DukeException("I didn't detect any file with expenses in it! I'll load an empty list first.");
        } catch (IOException e) {
            throw new DukeException("I had an issue reading your items from memory! I'll load an empty list first.");
        } catch (ClassNotFoundException e) {
            throw new DukeException("Your data does not resemble any format I know. I'll load an empty list first.");
        }
    }

}
