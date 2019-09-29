package duke.storage;

import duke.tasklist.TaskList;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    /**
     * Method to read saved tasks from file.
     */

    public static String readFile() throws Exception {
        String contents = "";
        FileReader reader = new FileReader("../duke.txt");
        int i;
        while ((i = reader.read()) != -1) {
            contents += (char) i;
        }
        reader.close();

        return contents;
    }

    /**
     * Method to save tasks to file.
     */
    public static void saveFile(TaskList tasks) {
        try {
            FileWriter fstream = new FileWriter("data/duke.txt");
            BufferedWriter out = new BufferedWriter(fstream);
            for (int i = 0; i < tasks.getSize(); i++) {
                out.write(tasks.getTask(i).saveString());
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
