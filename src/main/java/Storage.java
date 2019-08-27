import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        ArrayList<Task> loadedTaskList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String currTask = sc.nextLine();
            if (currTask.charAt(0) == 'T') {
                String[] currArray = currTask.split(" \\| ");
                Todo currTodo = new Todo(currArray[2]);
                if (currArray[1].equals("1")) {
                    currTodo.markAsDone();
                }
                loadedTaskList.add(currTodo);
            } else if (currTask.charAt(0) == 'D') {
                String[] currArray = currTask.split(" \\| ");
                Deadline currDeadline = new Deadline(currArray[2], currArray[3]);
                if (currArray[1].equals("1")) {
                    currDeadline.markAsDone();
                }
                loadedTaskList.add(currDeadline);
            } else {
                String[] currArray = currTask.split(" \\| ");
                Event currEvent = new Event(currArray[2], currArray[3]);
                if (currArray[1].equals("1")) {
                    currEvent.markAsDone();
                }
                loadedTaskList.add(currEvent);
            }
        }
        sc.close();
        return loadedTaskList;
    }

    public void store() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(getSavedListString());
        fw.close();
    }

    public static String getSavedListString() {
        String allTasks = "";
        for (int i = 0; i < TaskList.myTasks.size(); i++) {
            allTasks += TaskList.myTasks.get(i).toSave() + "\n";
        }
        return allTasks;
    }
}