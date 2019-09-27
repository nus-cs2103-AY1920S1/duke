package driver;

import task.TaskList;
import task.Task;
import task.TodoTask;
import task.DeadlineTask;
import task.EventTask;
import formatter.TimeFormatter;
import command.ErrorCommand;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Storage class controls the file reading and file writing functions of Duke.
 */

class Storage {
    private String filePath;

    /**
     * Constructor for Storage stores filepath specified by user.
     *
     * @param x String of filepath
     */

    Storage(String x) {
        filePath = x;
    }

    /**
     * loadTasks take in a taskList and stores all tasks read from an external file.
     * into the taskList
     *
     * @param myList taskList to store all the tasks that are read from the file
     * @throws FileNotFoundException if filepath is invalid
     */

    void loadTasks(TaskList myList) {
        ArrayList<Task> myTasksStore = myList.getList();
        File f = new File(filePath); // create a File for the given file path
        try {
            f.getParentFile().mkdir();
            f.createNewFile();
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNextLine()) {
                String current = s.nextLine();
                String[] splitTask = current.split(",");

                switch (splitTask[0]) {
                case "T": {
                    myTasksStore.add(
                            new TodoTask(splitTask[2], Boolean.parseBoolean(splitTask[1])));
                    break;
                }

                case "E": {
                    myTasksStore.add(
                            new EventTask(splitTask[2], Boolean.parseBoolean(splitTask[1]),
                                    TimeFormatter.convertToDate(splitTask[3]),
                                    TimeFormatter.convertToDate(splitTask[4])));
                    break;
                }

                case "D": {
                    myTasksStore.add(
                            new DeadlineTask(splitTask[2],
                                    Boolean.parseBoolean(splitTask[1]),
                                    TimeFormatter.convertToDate(splitTask[3])));
                    break;
                }
                default: {
                }
                }
            }
            s.close();
        } catch (IOException noFile) {
            ErrorCommand myErr = new ErrorCommand(noFile);
            System.out.printf(myErr.executeCommand());
        } catch (ParseException parseError) {
            System.out.println("This is impossible");
        }

    }


    /**
     * updateTasks takes in a taskList and stores all tasks read from the tasklist.
     * into an external file
     *
     * @param myList taskList is a reference for the new tasks
     * @throws IOException if filepath is invalid
     */

    void updateTasks(TaskList myList) throws IOException {
        ArrayList<Task> myTasksStore = myList.getList();
        FileWriter fw = new FileWriter(filePath);

        for (Task current : myTasksStore) {
            String task = "";
            switch (current.getType()) {

            case "T": {
                TodoTask todo = (TodoTask) current;
                task = String.format("%s,%b,%s",
                        todo.getType(),
                        todo.getDoneStatus(),
                        todo.getName());
                break;
            }
            case "E": {
                EventTask event = (EventTask) current;

                task = String.format("%s,%b,%s,%s,%s",
                        event.getType(),
                        event.getDoneStatus(),
                        event.getName(),
                        TimeFormatter.convertToStringStore(event.getStartTime()),
                        TimeFormatter.convertToStringStore(event.getEndTime()));
                break;
            }
            case "D": {
                DeadlineTask deadline = (DeadlineTask) current;
                task = String.format("%s,%b,%s,%s",
                        deadline.getType(),
                        deadline.getDoneStatus(),
                        deadline.getName(),
                        TimeFormatter.convertToStringStore(deadline.getTime()));
                break;
            }
            default: {
            }
            }
            fw.write(task + System.lineSeparator());
        }
        fw.close();
    }
}





