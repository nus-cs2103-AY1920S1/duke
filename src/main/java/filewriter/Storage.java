package filewriter;

import datetime.DateTime;
import exception.DukeException;
import task.*;


import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class used to store and read from text file.
 */
public class Storage {
    String filePath;
    FileWriter fw;
    ArrayList<Task> taskList;
    boolean willAppend = true;

    /**
     * Constructor of Storage object.
     * @param filepath filepath to text file to be read from and edited.
     * @throws DukeException When CreateWriter throws DukeException.
     */
    public Storage(String filepath) throws DukeException {
        this.filePath = filepath;
        this.createWriter();
    }

    /**
     * Creates FileWriter member fw.
     * @throws DukeException when invalid filepath.
     */
    private void createWriter() throws DukeException{
        try{
            fw = new FileWriter(filePath, willAppend);
        } catch (IOException e) {
            throw new DukeException("invalid filepath");
        }
    }

    /**
     * Wrapper to close FileWriter fw.
     * Used in Execute method of ExitCommand object.
     * @throws DukeException when invalid filepath.
     */
    public void closeWriter() throws DukeException{
        try {
            fw.close();
        } catch (IOException e) {
            throw new DukeException("invalid filepath");
        }
    }

    /**
     * Adds Task to TaskList data stored in txt file.
     * Called during execute method of addCommand object.
     * @param textToAdd (Command Object).to String();
     * @throws DukeException thrown when IOException is caught.
     */
    public void writeToFile(String textToAdd) throws DukeException{
        try {
            checkAppend(true);
            fw.write(textToAdd + "\n");
            //System.out.println(textToAdd);
        } catch (IOException e) {
            throw new DukeException("failed to write to file");
        }
    }

    /**
     * Rewrites data in txt file with editions made.
     * Called when Execute command of DeleteCommand or EditCommand object modifies TaskList.
     * @param schedule Modified contents of TaskList.
     * @throws DukeException thrown when IOException is caught.
     */
    public void editFile(TaskList schedule) throws DukeException{
        try {
            checkAppend(false);
            for (Task task: schedule.getList()) {
                fw.write(task.toString() + "\n");
            }
        } catch (IOException e) {
            throw new DukeException("failed to write to file");
        }
    }

    /**
     * toggles willAppend boolean, which determines if FileWriter fw will append to txt file or overwrite it.
     * @param toAppend new boolean value of toAppend.
     * @throws DukeException thrown when createWriter throws DukeException.
     */
    private void checkAppend(boolean toAppend) throws DukeException{
        closeWriter();
        willAppend = toAppend;
        createWriter();
    }

    /**
     * Method to interpret each line in txt file and add corresponding Task to TaskList.
     * @param line every line in the txt file represents a task.
     * @return Task object with type, task name, date and timing as described in String.
     * @throws DukeException thrown when Task is not stored in the correct format in txt file.
     */
    private Task read(String line) throws DukeException {
        Task output;
        switch (line.charAt(line.indexOf("[") + 1)) {
            case 'T':
                output = new Todo(line.substring(7, line.length()));
                return output;
            case 'D':
                try {
                    int divider = line.indexOf("(by:");
                    String input = line.substring(7, divider);
                    input += "/by " + DateTime.readDeadLine(line.substring(divider + 5, line.length() - 1)).toString();
                    output = new Deadline(input);
                    return output;
                } catch (Exception e) {
                    throw new DukeException("Deadline task not stored properly.");
                }
            default:
                try {
                    int divider = line.indexOf("(at:");
                    String input = line.substring(7, divider);
                    input += "/at " + DateTime.readEventTime(line.substring(divider + 5, line.length() - 1)).toString();
                    output = new Event(input);
                    return output;
                } catch (Exception e) {
                    throw new DukeException("Event task not stored properly.");
                }
        }
    }

    /**
     * Reads txt file and updates TaskList accordingly.
     * Calls read method line by line, and gets corresponding Task.
     * Check each line to check if Task should markAsDone.
     * @return Storage object with updated ArrayList<Task> taskList used in construction of TaskList object.
     * @throws DukeException when read throws DukeException.
     */
    public Storage load() throws DukeException {
        try {
            taskList = new ArrayList<>();
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (! line.equals("")) {
                    Task newTask = read(line.replace("\n", ""));
                    if (line.substring(4,5).equals("\u2713")){
                        newTask.markAsDone();
                    }
                    taskList.add(newTask);
                }
            }
            return this;
        } catch (FileNotFoundException e){
            throw new DukeException("File not found!");
        } catch (Exception e){
            throw new DukeException("Unforseen load errors: " + e.getMessage());
        }
    }

    /**
     * Called during construction method of TaskList.
     * @return ArrayList of Task
     */
    public ArrayList<Task> getSchedule(){
        return taskList;
    }
}
