package utils;

import task.Task;
import task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Manages the I/O for accessing and updating of a stored task list in
 * the file directory.
 */
public class Storage {
    private static final String SEPARATOR = " | ";
    private static File file;

    private TaskList taskList;

    /**
     * Loads data into the TaskList if the storage file already exists. Else
     * creates a new file.
     *
     * @param absolutePathName The full path name of the file to specify its
     *                         stored location.
     */
    public Storage(String absolutePathName) {
        file = new File(absolutePathName);
        taskList = TaskList.newInstance();
        if (file.exists()) {
            loadData();
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Deletes the storage file from its directory.
     */
    public void deleteData() {
        file.delete();
    }

    /**
     * Parse the storage file and stores all the tasks listed within it
     * into TaskList.
     */
    private void loadData() {
        Scanner sc;
        assert file.exists() : "Storage file does not exist.";
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        while (sc.hasNext()) {
            // Separates a line "E | 0 | EventName | 20/08/2019 2100" to
            // {"E", "0", "EventName", "20/08/2019 2100"}
            String[] taskInfo = sc.nextLine().split("\\s*\\|\\s*");
            addNewTaskToTaskList(taskInfo);
        }
    }

    /**
     * Rewrites the whole storage file based on the current TaskList
     * contents.
     */
    public void updateData() {
        try {
            FileWriter fw = new FileWriter(file.getAbsolutePath());
            TaskList taskList = TaskList.newInstance();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                sb.append(task.getStorageStringFormat());
                sb.append("\n");
            }
            fw.write(sb.toString());
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addNewTaskToTaskList(String[] taskInfo) {
        switch (taskInfo[0]) {
        case "T":
            taskList.addNewTodoTask(taskInfo[2],
                    taskInfo[1].equals(Task.DONE + ""));
            break;

        case "D":
            taskList.addNewDeadlineTask(taskInfo[2],
                    taskInfo[3],
                    taskInfo[1].equals(Task.DONE + ""));
            break;

        case "E":
            taskList.addNewEventTask(taskInfo[2],
                    taskInfo[3],
                    taskInfo[1].equals(Task.DONE + ""));
            break;

        default:
            break;
        }
    }

}
