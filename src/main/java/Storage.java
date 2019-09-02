import java.io.FileWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

/**
 *  Represents the file to load-up and save the TaskList.
 */

public class Storage {

    private String filepath;

    /**
     * Constructs a storage object.
     * @param filepath refers to the file path of TaskList
     */


    public Storage (String filepath) {
        this.filepath = filepath;
    }

    /**
     * WriteToFile method to only be used by Storage class.
     * Saves the taskslist to the file.
     * @param filePath the directory of the TaskList file, in the form of a String
     * @param textToAdd text that is meant to be appended
     * @throws IOException
     */
    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // boolean param to append and not overwrite
        fw.append(textToAdd);
        fw.close();
    }
    /**
     * Loads up the tasklist from the saved file in the harddisk.
     * @return ArrayList of tasks.
     * @throws DukeException if the file is not found.
     */

    public ArrayList<Task> load() throws DukeException{
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File loadUpFile = new File(this.filepath);
            Scanner scLoad = new Scanner(loadUpFile);
            while (scLoad.hasNextLine()) {
                String sentence = scLoad.nextLine();
                String[] taskInfo = sentence.split(" \\u007C ");
                if (taskInfo[0].equals("T")) {
                    ToDo taskT = new ToDo(taskInfo[2]);
                    if (taskInfo[1].equals("1")) {
                        taskT.markAsDone();
                    }
                    tasks.add(taskT);
                } else if (taskInfo[0].equals("D")) {
                    Deadline taskD = new Deadline(taskInfo[2]);
                    try {
                        taskD.parseTime(taskInfo[3]);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (taskInfo[1].equals("1")) {
                        taskD.markAsDone();
                    }
                    tasks.add(taskD);
                } else if (taskInfo[0].equals("E")) {
                    Event taskE = new Event(taskInfo[2]);
                    try {
                        taskE.parseTime(taskInfo[3]);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (taskInfo[1].equals("1")) {
                        taskE.markAsDone();
                    }
                    tasks.add(taskE);
                } else {

                }
            }
            scLoad.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Error, File Not Found.");
        }
        return tasks;
    }

    public void writeToHardDisk(TaskList tasks) throws DukeException{
        try {
            String taskListFilePath = "C:\\repos\\duke\\out\\data\\TaskList.txt";
            FileWriter fw = new FileWriter (taskListFilePath);
            for (int i = 0; i < tasks.getSize(); i++) {
                if (tasks.getTask(i) instanceof Deadline) {
                    writeToFile(taskListFilePath, "D | " + tasks.getTask(i).isDone() + " | "
                            + tasks.getTask(i).getTask() + " | " + ((Deadline) tasks.getTask(i)).getDueDate());
                } else if (tasks.getTask(i) instanceof Event) {
                    writeToFile(taskListFilePath, "E | " + tasks.getTask(i).isDone() + " | "
                            + tasks.getTask(i).getTask() + " | " + ((Event) tasks.getTask(i)).getDueDate());
                } else {
                    writeToFile(taskListFilePath, "T | " + tasks.getTask(i).isDone() + " | "
                            + tasks.getTask(i).getTask());
                }
                if (i != tasks.getSize() - 1) {
                    writeToFile(taskListFilePath, "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred " + e.getMessage());
        }
    }
}
