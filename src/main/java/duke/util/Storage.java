package duke.util;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private Scanner sc;
    private ArrayList<String> contentList = new ArrayList<String>();
    private static final String DUKE_DATA_DIR = System.getProperty("user.home") + File.separator + "data";
    private static final String DUKE_DATA_PATH = DUKE_DATA_DIR + File.separator + "task.txt";

    public Storage() {
        try {
            if (!Paths.get(DUKE_DATA_PATH).toFile().exists()) {
                Files.createDirectory(Paths.get(DUKE_DATA_DIR));
                Files.createFile(Paths.get(DUKE_DATA_PATH));
            }
            File f = new File(DUKE_DATA_PATH);
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("You have no task.");
        } catch (IOException e) {
            System.out.println("Problem occurred while creating a new file in Storage");
            assert (false);
        }
    }

    public ArrayList<String> load() {
        try {
            while (sc.hasNext()) {
                String s = sc.nextLine();
                contentList.add(s);
            }
            return contentList;
        } catch (Exception e) {
            System.out.println("No task were detected");
        } finally {
            return contentList;
        }
    }

    public void save(ArrayList<Task> list) {
        try {
            FileWriter fw = new FileWriter(DUKE_DATA_PATH);
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
