package duke.util;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private Scanner sc;
    private ArrayList<String> contentList = new ArrayList<String>();

    public Storage(String filePath) {
        try {
            File f = new File(filePath);
            f.createNewFile();
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("You have no task.");
        } catch (IOException e) {
            System.out.println("Problem occurred while creating a new file in Storage");
            assert (false);
        }
    }

    public ArrayList<String> load() {
        while (sc.hasNext()) {
            String s = sc.nextLine();
            contentList.add(s);
        }
        return contentList;
    }

    public void save(ArrayList<Task> list) {
        try {
            FileWriter fw = new FileWriter("data/task.txt");
            String fileContent = "";
            for (Task t : list) {
                fileContent += t.toWriteFile() + "\n";
            }
            fw.write(fileContent);
            fw.close();
        } catch (IOException e) {
            System.out.println("File does not exist");
        }
    }
}
