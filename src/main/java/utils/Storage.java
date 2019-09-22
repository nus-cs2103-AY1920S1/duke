package utils;

import error.DukeException;
import task.Task;
import task.Todo;
import task.Event;
import task.Deadline;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    public File file;
    public File dir;
    public String filepath;

    /**
     * Creates a instance of Storage object.
     *
     * @param filepath String of the file location
     */
    public Storage(String filepath) {
        String[] arr = filepath.split("/");
        dir = new File(arr[0]);
        dir.mkdirs();

        this.dir = dir;
        this.file = new File(dir, arr[1]);
        this.filepath = arr[1];
    }

    /**
     * Transforms data in text file to TaskLists containing list of Tasks.
     *
     * @returns TaskList containing list of Tasks
     * @throws Exception if file does not exist or loading task error
     */
    public ArrayList<Task> load() throws Exception {
        try {
            if (file.exists()) {
                return loadTasks();
            } else {
                file.createNewFile();
                throw new DukeException("New Path: File does not exist");
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Helper Function to load() method.
     *
     * @return list of tasks from text file
     * @throws IOException if task type != "T" || "D" || "E"
     * */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split(" \\| ");
                Task task;

                switch (arr[0]) {
                case "T": //todo
                    task = new Todo(arr[2]);
                    break;
                case "D": //deadline
                    task = new Deadline(arr[2], arr[3]);
                    break;
                case "E": //event
                    task = new Deadline(arr[2], arr[3]);
                    break;
                default:
                    throw new AssertionError("LOAD FILE FAILURE: invalid task type " + arr[0]);
                }

                if (Integer.parseInt(arr[1]) == 1) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Adds new tasks information in text file.
     *
     * @param task Task to save in file
     * @param type Type of task of one Char length
     * */
    public void saveTasks(Task task, String type) {
        try {
            FileWriter writer = new FileWriter(file, true); //initialize file writer
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            int status;
            if (task.getStatus() == true) {
                status = 1;
            } else {
                status = 0;
            }
            bufferedWriter.write(type + " | " + status + " | " + task.getDescription());
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Edits existing tasks in file through delete or update.
     *
     * @param task Task to save in file
     * @param command String command from user input
     * @param position Index of task in TaskList
     * */
    public void updateTasks(Task task, String command, int position) {
        try {
            File temp = new File(dir, "temp.txt");
            FileWriter writer = new FileWriter(temp);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            FileReader reader = new FileReader(file); //initialize file reader
            BufferedReader bufferedReader = new BufferedReader(reader);

            int counter = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (counter != position && (!command.equals("delete") || !command.equals("done"))) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                    counter++;
                    continue;
                }

                // DONE COMMAND: counter == position
                if (command.equals("done")) {
                    int status = 1;
                    bufferedWriter.write(task.getType() + " | " + status + " | " + task.getDescription());
                    bufferedWriter.newLine();
                }
                counter++;
                // DELETE COMMAND: ignore current line
            }
            //overwrite master txt copy
            bufferedWriter.close();
            file.delete();
            file = new File(dir, filepath);
            temp.renameTo(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}