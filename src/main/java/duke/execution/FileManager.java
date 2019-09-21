package duke.execution;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;
import duke.task.*;

/**
 * FileManager class of Duke.
 * Handles reading and writing to .txt save file in the local system.
 */
public class FileManager {
    private File saveList;
    private FileWriter editor;
    private Scanner sc;
    private boolean isActive = true;

    public FileManager(){
    }

    /**
     * Links FileManager with the save file from local system. And loads existing tasks if it exists
     * @param pathname file path of local save file
     * @param tasks to match TaskManager with existing tasks from the save file
     * @return boolean value to inform user if there is an existing save file
     * @throws DukeException if save file is corrupted/cannot be found
     */
    public boolean initialize(String pathname, TaskManager tasks) throws DukeException{
        File directory = new File("./data");
        boolean isNew;
        try {
            if(!directory.exists()) {
                directory.mkdirs();
            }
            saveList=new File(pathname);
            isNew = saveList.createNewFile();
            editor = new FileWriter(saveList, true);
            if(!isNew){
                sc = new Scanner(saveList);
                this.transferSavedFile(tasks);
            }
            return isNew;
        }catch(IOException e){
            this.isActive = false;
            throw new DukeException("Directory search error. No save file loaded. Initialized with blank list. " +
                    "No commands will be saved to hard disk");
        }catch(DukeException e){
            throw new DukeException("Bad datetime in saved file. List may be partially loaded. Editing not recommended");
        }catch(ArrayIndexOutOfBoundsException e){
            throw new DukeException("Corrupted save file. Delete or edit then relaunch");
        }
    }

    /**
     * Transfers tasks in the save file with the todoList in the TaskManager instance
     * @param taskList TaskManager instance of the current Duke process
     * @throws DukeException
     */
    private void transferSavedFile(TaskManager taskList) throws DukeException{
        if(!this.isActive){
            return;
        }
        int index = 0;
        while(sc.hasNext()){
            String saveEntry = sc.nextLine();
            String instruction[] = saveEntry.split(" \\| ");
            if(instruction[0].equals("T")){
                Todo task = new Todo(instruction[2]);
                taskList.load(task);
            }else if(instruction[0].equals("E")){
                Event task = new Event(instruction[2], instruction[3]);
                taskList.load(task);
            }else if(instruction[0].equals("D")){
                Deadline task = new Deadline(instruction[2], instruction[3]);
                taskList.load(task);
            }
            if(instruction[1].equals("1")){
                taskList.savedDone(index);
            }
            index++;
        }
        sc.close();
    }

    /**
     * Saves new task to save file
     * @param task New task created
     * @throws DukeException
     */
    protected void saveToFile(Task task) throws DukeException{
        if(!isActive){
            return;
        }
        try {
            editor.write(task.toFileString());
            editor.flush();
        } catch (IOException e){
            throw new DukeException("File write error");
        }
    }

    /**
     * Saves task status to done in the save file
     * @param doneIndex Index of task to change
     * @throws DukeException
     */
    protected void fileSetDone(int doneIndex) throws DukeException{
        if(!isActive){
            return;
        }
        StringBuilder finalInput = new StringBuilder();
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(saveList.toPath(), StandardCharsets.UTF_8));
            StringBuilder editString = new StringBuilder(fileContent.get(doneIndex));
            editString.setCharAt(4, '1');
            fileContent.set(doneIndex, editString.toString());
            for(String s: fileContent){
                finalInput.append(s + "\n");
            }
            FileWriter overwriteEditor = new FileWriter(saveList, false);
            overwriteEditor.write(finalInput.toString());
            overwriteEditor.close();
        } catch (IOException e){
            throw new DukeException("File write error");
        }
    }

    /**
     * Deletes a task in the save file
     * @param deleteIndex Index of task to delete
     * @throws DukeException
     */
    protected void fileDelete(int deleteIndex) throws DukeException{
        if(!isActive){
            return;
        }
        StringBuilder finalInput = new StringBuilder();
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(saveList.toPath(), StandardCharsets.UTF_8));
            fileContent.remove(deleteIndex);
            for(String s: fileContent){
                finalInput.append(s + "\n");
            }
            FileWriter overwriteEditor = new FileWriter(saveList, false);
            overwriteEditor.write(finalInput.toString());
            overwriteEditor.close();
        } catch (IOException e){
            throw new DukeException("File write error");
        }
    }

    protected void fileEditTime(int editIndex, Task editedTask) throws DukeException{
        StringBuilder finalInput = new StringBuilder();
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(saveList.toPath(), StandardCharsets.UTF_8));
            fileContent.set(editIndex, editedTask.toFileString());
            for(String s: fileContent){
                finalInput.append(s + "\n");
            }
            FileWriter overwriteEditor = new FileWriter(saveList, false);
            overwriteEditor.write(finalInput.toString());
            overwriteEditor.close();
        } catch (IOException e){
            throw new DukeException("File write error");
        }

    }
}
