package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import duke.parser.IncorrectFileFormatException;
import duke.parser.Parser;
import duke.task.Task;
import duke.ui.Ui;


// This class loads task from file and saves tasks to file.

/**
 * Represents a location in a 2D space. A <code>Point</code> object corresponds to
 * a coordinate represented by two integers e.g., <code>3,6</code>
 */
public class Storage {
    private String targetFilePath;
    private String printFilePath = "C:\\Users\\user\\Desktop\\CS2103_Git\\duke\\data\\print.txt";

    public Storage(String filePath) {
        // Convert string filePath to actual filepath and store in FilePath class
        targetFilePath = filePath;
    }

    // Method loads a file and process
    // Returns object TaskList

    public ArrayList<Task> load() throws IncorrectFileFormatException, FileNotFoundException {
        File f;
        //System.out.println(targetFilePath);
        f = new File(targetFilePath);

        Scanner s = new Scanner(f, "Unicode");
        ArrayList<String> listInput = new ArrayList<>();

        while (s.hasNextLine()) {
            String i = s.nextLine();
            listInput.add(i);
        }

        ArrayList<Task> listTask = new ArrayList<>();
        for (int i = 0; i < listInput.size(); i++) {
            try {
                listTask.add(Parser.parseFromFile(listInput.get(i)));
            } catch (IncorrectFileFormatException f1) {
                throw new IncorrectFileFormatException();
            } catch (NullPointerException n) {
                throw new NullPointerException();
            }
        }

        /*
        System.out.println("test print");
        for (int i = 0; i < listTask.size(); i++) {
            System.out.println(listTask.get(i).printTask());
        }

         */

        return listTask;
    }

    public void save(ArrayList<String> l){
        try {
            FileWriter fw = new FileWriter(printFilePath);
            for(int i = 0; i < l.size(); i++) {
                fw.write(l.get(i) + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}

