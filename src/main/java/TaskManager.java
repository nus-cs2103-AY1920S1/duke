//File object serves as your reference to the hard drive file
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
//Read input
import java.util.Scanner;
import java.util.ArrayList;

public class TaskManager {
    String filePath;

    public TaskManager() {
    filePath ="data/loggedData.txt";
    }

    protected void loadTasks(TaskList myList) throws FileNotFoundException {
        ArrayList<Task> myTasksStore = myList.getList();
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNextLine()) {
            String current = s.nextLine();
            String[] splitTask = current.split(",");
            if (splitTask[0].equalsIgnoreCase("T")) {
                myTasksStore.add(new todoTask(splitTask[2], Boolean.parseBoolean(splitTask[1])));
            } else if (splitTask[0].equalsIgnoreCase("D")) {
                myTasksStore.add(new deadlineTask(splitTask[2], Boolean.parseBoolean(splitTask[1]), splitTask[3]));
            } else if (splitTask[0].equalsIgnoreCase("E")) {
                myTasksStore.add(new deadlineTask(splitTask[2], Boolean.parseBoolean(splitTask[1]), splitTask[3]));
            }
        }
        s.close();
    }

    protected void updateTasks(TaskList myList) throws IOException {
        ArrayList<Task> myTasksStore = myList.getList();
        FileWriter fw = new FileWriter(filePath);
        for(Task current:myTasksStore) {
            if((current.getType()).equalsIgnoreCase("T")) {
                  String task = String.format("%s,%b,%s", current.getType(),current.getDoneStatus(), current.getName());
                fw.write(task+ System.lineSeparator());
            } else {
                if((current.getType()).equalsIgnoreCase("E")) {
                    eventTask event = (eventTask)current;
                    String task = String.format("%s,%b,%s,%s", event.getType(),event.getDoneStatus(),event.getName(),event.getTime());
                    fw.write(task+ System.lineSeparator());
                } else {
                    deadlineTask deadline = (deadlineTask)current;
                    String task = String.format("%s,%b,%s,%s", deadline.getType(),deadline.getDoneStatus(),deadline.getName(),deadline.getTime());
                    fw.write(task+System.lineSeparator());
                }

                }
        }

fw.close();
        }


    }





