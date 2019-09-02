package duke;

import duke.task.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class DataStorage {
    private static final String FILE_NAME = "duke.txt";
    private static final String PARENT_DIR_NAME = "data";
    private static final int SEARCH_LIMIT = 5;
    private String path;

    public DataStorage() {
        this.setFilePath();
    }
    /**
     * Stores all tasks in tasklist into given txt file
     * @param taskList - list containing all existing tasks
     */
    public void storeTaskList(TaskList taskList) {
        String content = getStringContent(taskList);
        try {
            Files.writeString(Paths.get(this.path), content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves tasks from given txt file and stores into new duke.task.TaskList
     * @return duke.task.TaskList - list containing all existing tasks
     */
    public TaskList getStoredTaskList() {
        File file = new File(this.path);
        TaskList taskList = new TaskList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String command;
            int idx = 0;
            while ((command = br.readLine()) != null) {
                String[] taskInfo = command.split("\\|");
                switch (taskInfo[0]) {
                case "Deadline":
                    taskList.add(new DeadlineTask(taskInfo[2], taskInfo[3]));
                    break;
                case "Event":
                    taskList.add(new EventTask(taskInfo[2], taskInfo[3]));
                    break;
                case "Todo":
                    taskList.add(new ToDoTask(taskInfo[2]));
                    break;
                }
                if (taskInfo[1].equals("1")) { taskList.done(idx); }
                idx++;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return taskList;
    }

    /**
     * Converts existing taskList into storable and easily retrievable string format
     * @param taskList - list containing all existing tasks
     * @return String format of taskList
     */
    private static String getStringContent(TaskList taskList) {
        String contents = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String taskType = task.getTaskType();
            String isDone = (task.isDone()) ? "1" : "0";
            String taskName = task.getName();
            switch (task.getTaskType()) {
            case "Deadline":
                DeadlineTask deadLineTask = (DeadlineTask) task; // Check coding standard
                String deadline = deadLineTask.getDeadline();
                contents += taskType + "|" + isDone + "|" + taskName + "|" + deadline + "\n";
                break;
            case "Event":
                EventTask eventTask = (EventTask) task;
                String duration = eventTask.getDuration();
                contents += taskType + "|" + isDone + "|" + taskName + "|" + duration + "\n";
                break;
            case "Todo":
                contents += taskType + "|" + isDone + "|" + taskName + "\n";
                break;
            }
        }
        return contents;
    }

    //@@author {ang-zeyu}-reused
    //Reused from https://github.com/ang-zeyu/duke/blob/master/src/main/java/duke/storage/Storage.java with minor modifications
    private void setFilePath() {
        // Gets the currents directory of the user
        String workingDirectory = System.getProperty("user.dir");
        Path directory = Paths.get(workingDirectory);
        int count = 1;
        while (!Files.isDirectory(Paths.get(directory.toString(), PARENT_DIR_NAME))
                && count <= SEARCH_LIMIT) {
            directory = directory.getParent();
            count++;
        }
        if (count > SEARCH_LIMIT) {
            // Create new directory
            try {
                Path newPath = Paths.get(workingDirectory, PARENT_DIR_NAME);
                Files.createDirectory(newPath);
                this.path = Paths.get(newPath.toString(), FILE_NAME).toString();
            } catch (IOException e) {
                System.out.println("Storage location not found :( ");
            }
        } else {
            this.path = Paths.get(directory.toString(), PARENT_DIR_NAME, FILE_NAME).toString();
        }
    }
}