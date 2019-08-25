package task;

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

    /**
     * Reads data from data file.
     * 
     * @return New TaskList that contains an ArrayList of tasks and number of tasks.
     * @throws FileNotFoundException Occurs when path of file is invalid.
     */
    public TaskList readData() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        ArrayList<Task> task = new ArrayList<>();
        while (sc.hasNext()) {
            String taskLine = sc.nextLine();
            String[] taskLineSplit = taskLine.split(":");
            switch (taskLineSplit[0]) {
            case "T":
                task.add(new Todo(taskLineSplit[2], Integer.parseInt(taskLineSplit[1])));
                break;
            case "E":
                task.add(new Event(taskLineSplit[2], taskLineSplit[3], Integer.parseInt(taskLineSplit[1])));
                break;
            case "D":
                task.add(new Deadline(taskLineSplit[2], taskLineSplit[3], Integer.parseInt(taskLineSplit[1])));
                break;
            default:
            }
        }

        // Close the scanner
        sc.close();

        return new TaskList(task);
    }

    /**
     * Write data from taskList into data file.
     * 
     * @param taskList A TaskList that contains an ArrayList of tasks and number of
     *                 tasks.
     * @throws IOException Occurs when path of file is invalid.
     */
    public void writeData(TaskList taskList) throws IOException {
        ArrayList<Task> task = taskList.getTasks();
        FileWriter fw = new FileWriter(filePath);
        String stringToWrite = "";
        int counter = task.size();
        for (int i = 0; i < counter; i++) {
            Task taskToWrite = task.get(i);
            if (taskToWrite instanceof Todo) {
                stringToWrite += "T:";
                stringToWrite += writeStatus(taskToWrite);
                stringToWrite += taskToWrite.getDescription();
            } else if (taskToWrite instanceof Event) {
                stringToWrite += "E:";
                stringToWrite += writeStatus(taskToWrite);
                stringToWrite += taskToWrite.getDescription() + ":";
                Event e = (Event) taskToWrite;
                stringToWrite += e.getTime();
            } else if (taskToWrite instanceof Deadline) {
                stringToWrite += "D:";
                stringToWrite += writeStatus(taskToWrite);
                stringToWrite += taskToWrite.getDescription() + ":";
                Deadline d = (Deadline) taskToWrite;
                stringToWrite += d.getTime();
            }

            if (i != counter - 1) {
                stringToWrite += System.lineSeparator();
            }
        }
        fw.write(stringToWrite);
        fw.close();
    }

    private String writeStatus(Task t) {
        String statusIcon = t.getStatusIcon();
        if (statusIcon.equals("O")) {
            return "1:";
        } else {
            return "0:";
        }
    }
}