package duke.storage;

import duke.task.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    String filePath;
    // keep track of number of tasks
    private static int count = 0;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadFile() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        // create a file object
        File f = new File(filePath);
        Scanner load = new Scanner(f);
        while (load.hasNext()) {
            String item = load.nextLine();
            loadTask(item, tasks);
        }
        return tasks;
    }

    private static void loadTask(String item, ArrayList<Task> tasks) {
        if (item.startsWith("T")) {
            // split command into 3 parts
            String[] splitStr = item.split(" \\| ", 3);
            tasks.add(new Todo(splitStr[2]));
            if (splitStr[1].equals("1")) {
                tasks.get(count).setDone();
            }
            count ++;
        } else if (item.startsWith("D")) {
            // split command into 4 parts
            String[] splitStr = item.split(" \\| ", 4);
            tasks.add(new Deadline(splitStr[2], splitStr[3]));
            if (splitStr[1].equals("1")) {
                tasks.get(count).setDone();
            }
            count ++;
        } else if (item.startsWith("E")) {
            // split command into 4 parts
            String[] splitStr = item.split(" \\| ", 4);
            tasks.add(new Event(splitStr[2], splitStr[3]));
            if (splitStr[1].equals("1")) {
                tasks.get(count).setDone();
            }
            count ++;
        }
    }

    public void saveFile(TaskList tasklist) throws IOException {
        FileWriter save = new FileWriter(filePath);
        for (int i = 0; i < tasklist.size(); i++) {
            save.write(tasklist.get(i).saveTask());
        }
        save.close();
    }

}
