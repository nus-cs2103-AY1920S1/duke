package duke.core;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Deadline;
import duke.tasks.Event;

import duke.errors.DukeException;
import duke.errors.DukeExceptionType;



/**
 * Represents the storage of the application. Provides methods that to overwrite
 * the contents of the file and loading data from the file.
 */
public class Storage{

    private File file;


    /**
     * Initialises the Storage with the filepath of the file
     *
     * @param filePath String of the directory the file is in.
     */
    public Storage(String filePath){
        this.file = new File(filePath);
    }


    /**
     * Reads the data stored in the file, after which the date would be used
     * to generate a ArrayList that would be returned.
     *
     * @return An ArrayList of tasks.
     * @throws DukeException Thrown when the file does not exist.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                taskList.add(formatFileToTask(line));
            }
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeException("Load failed", DukeExceptionType.FILE_NOT_FOUND);
        }
    }


    /**
     * Overwrites the data in the file by writing to the file.
     *
     * @param taskList Current task list stored in the application.
     * @throws IOException Thrown when writing to file fails.
     */
    void overwriteStorage(ArrayList<Task> taskList) throws IOException{
        assert (this.file != null);
        FileWriter fw = new FileWriter(this.file);
        for (Task task: taskList){
            switch (task.getType()) {
                case TODO_TASK:
                    fw.write(Task.getTaskID(task) + " / " +
                            "ToDo" + " / " +
                            task.getStorageStatusIcon() + " / " +
                            task.getDescription() +
                            System.lineSeparator());
                    break;
                case DEADLINE_TASK:
                    fw.write(Task.getTaskID(task) + " / " +
                            "Deadline" + " / " +
                            task.getStorageStatusIcon() + " / " +
                            task.getDescription() + " / " +
                            ((Deadline) task).getDate() +
                            System.lineSeparator());
                    break;
                case EVENT_TASK:
                    fw.write(Task.getTaskID(task) + " / " +
                            "Event" + " / " +
                            task.getStorageStatusIcon() + " / " +
                            task.getDescription() + " / " +
                            ((Event) task).getDate() +
                            System.lineSeparator());
                    break;
                default:
            }
        }
        fw.close();
    }

    //helper method to convert the written format of the task in the file
    //into a Task to be loaded back into storage
    private Task formatFileToTask(String line) throws DukeException {
        assert line != null;
        String[] tokens = line.split(" / ");
        switch(tokens[1]){
            case "ToDo":
                ToDo toDoTask = new ToDo(tokens[3]);
                if (tokens[2].equals("1")){
                    toDoTask.setDone();
                }
                return toDoTask;
            case "Deadline":
                Deadline deadlineTask = new Deadline(tokens[3], tokens[4]);
                if (tokens[2].equals("1")){
                    deadlineTask.setDone();
                }
                return deadlineTask;
            case "Event":
                Event eventTask = new Event(tokens[3], tokens[4]);
                if (tokens[2].equals("1")){
                    eventTask.setDone();
                }
                return eventTask;
            default:
                throw new DukeException("Unknown task detected? Something is wrong.",
                        DukeExceptionType.TASK_NOT_FOUND);
        }
    }


}