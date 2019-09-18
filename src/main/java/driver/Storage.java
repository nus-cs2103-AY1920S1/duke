package driver;

import task.TaskList;
import task.Task;
import task.TodoTask;
import task.DeadlineTask;
import task.EventTask;
import formatter.TimeFormatter;

//File object serves as your reference to the hard drive file
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
//Read input
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Storage class controls the file reading and file writing functions of Duke
 */

public class Storage {
    String filePath;

    /**
     * Constructor for Storage stores filepath specified by user
     *
     * @param x  String of filepath
     */

    public Storage(String x) {
    filePath =x;
    }

    /**
     * loadTasks take in a taskList and stores all tasks read from an external file
     * into the taskList
     *
     * @param myList taskList to store all the tasks that are read from the file
     * @throws FileNotFoundException if filepath is invalid
     */

    protected void loadTasks(TaskList myList) throws FileNotFoundException {
        ArrayList<Task> myTasksStore = myList.getList();
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNextLine()) {
            String current = s.nextLine();
            String[] splitTask = current.split(",");
            if (splitTask[0].equalsIgnoreCase("T")) {
                myTasksStore.add(new TodoTask(splitTask[2], Boolean.parseBoolean(splitTask[1])));
            } else if (splitTask[0].equalsIgnoreCase("D")) {
                myTasksStore.add(new DeadlineTask(splitTask[2], Boolean.parseBoolean(splitTask[1]), TimeFormatter.convertToDate(splitTask[3])));
            } else if (splitTask[0].equalsIgnoreCase("E")) {
                myTasksStore.add(new DeadlineTask(splitTask[2], Boolean.parseBoolean(splitTask[1]), TimeFormatter.convertToDate(splitTask[3])));
            } else {
                assert false:"Wrong input: " + current;
            }
        }
        s.close();
    }

    /**
     * updateTasks take in a taskList and stores all tasks read from the tasklist
     * into an external file
     *
     * @param myList taskList is a reference for the new tasks
     * @throws IOException if filepath is invalid
     */

    //Write new tasks at the end of the process
    protected void updateTasks(TaskList myList) throws IOException {
        ArrayList<Task> myTasksStore = myList.getList();
        FileWriter fw = new FileWriter(filePath);
        for(Task current:myTasksStore) {

            if((current.getType()).equalsIgnoreCase("T")) {
                  String task = String.format("%s,%b,%s", current.getType(),current.getDoneStatus(), current.getName());
                  fw.write(task+ System.lineSeparator());

            } else if ((current.getType()).equalsIgnoreCase("E")) {
                    EventTask event = (EventTask)current;
                    String task = String.format("%s,%b,%s,%s", event.getType(),event.getDoneStatus(),event.getName(),TimeFormatter.convertToString(event.getTime()));
                    fw.write(task+ System.lineSeparator());

            } else if ((current.getType()).equalsIgnoreCase("D")) {
                DeadlineTask deadline = (DeadlineTask) current;
                String task = String.format("%s,%b,%s,%s", deadline.getType(), deadline.getDoneStatus(), deadline.getName(), TimeFormatter.convertToString(deadline.getTime()));
                fw.write(task + System.lineSeparator());

            } else {
                assert false:current + " Something is wrong with your command";
                }
        }

fw.close();
        }


    }





