package utils;

import task.Task;
import task.Deadline;
import task.Event;
import task.Todo;
import task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * <p>
 *     Manages the I/O for accessing and updating of a stored task list in
 *     the file directory.
 * </p>
 */
public class Storage {
    private static final String SEPARATOR = " | ";

    private static File file;

    /**
     * <p>
     *     Loads data into the TaskList if the storage file already exists. Else
     *     creates a new file.
     * </p>
     *
     * @param absolutePathName The full path name of the file to specify its
     *                         stored location.
     */
    public Storage(String absolutePathName) {
        file = new File(absolutePathName);
        if (file.exists()) {
            loadData();
        } else {
            try {
                file.createNewFile();
                file = new File(file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Deletes the storage file from its directory.
     */
    public void clearData() {
        file.delete();
    }

    /**
     * <p>
     *     Parse the storage file and stores all the tasks listed within it
     *     into TaskList.
     * </p>
     */
    void loadData()  {
        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        TaskList taskList = TaskList.newInstance();
        while (sc.hasNext()) {
            String[] taskInfo = sc.nextLine().split("\\s*\\|\\s*");
            switch (taskInfo[0]) {
                case "T":
                    taskList.addNewTodoTask(taskInfo[2],
                            taskInfo[1].equals("1"));
                    break;

                case "D":
                    taskList.addNewDeadlineTask(taskInfo[2],
                            taskInfo[3],
                            taskInfo[1].equals("1"));
                    break;

                case "E":
                    taskList.addNewEventTask(taskInfo[2],
                            taskInfo[3],
                            taskInfo[1].equals("1"));
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * <p>
     *     Rewrites the whole storage file based on the current TaskList
     *     contents.
     * </p>
     */
    public void updateData()  {
        try {
            FileWriter fw = new FileWriter(file.getAbsolutePath());
            TaskList taskList = TaskList.newInstance();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                if (task instanceof Todo) {
                    sb.append("T");
                    sb.append(SEPARATOR);
                    sb.append(task.getStatus());
                    sb.append(SEPARATOR);
                    sb.append(task.getName());
                } else if (task instanceof Deadline) {
                    sb.append("D");
                    sb.append(SEPARATOR);
                    sb.append(task.getStatus());
                    sb.append(SEPARATOR);
                    sb.append(task.getName());
                    sb.append(SEPARATOR);
                    sb.append(task.getAdditionalInfo());
                } else if (task instanceof Event) {
                    sb.append("E");
                    sb.append(SEPARATOR);
                    sb.append(task.getStatus());
                    sb.append(SEPARATOR);
                    sb.append(task.getName());
                    sb.append(SEPARATOR);
                    sb.append(task.getAdditionalInfo());
                }

                if (i < taskList.size()) {
                    sb.append(System.lineSeparator());
                }
            }
            fw.write(sb.toString());
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
