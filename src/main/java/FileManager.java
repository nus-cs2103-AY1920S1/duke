import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private File saveList;
    private FileWriter editor;
    private Scanner sc;
    private boolean isActive = true;

    public FileManager(){
    }

    protected boolean initialize(String pathname, TaskManager tasks) throws DukeException{
        saveList=new File(pathname);
        boolean isNew;
        try {
            isNew = saveList.createNewFile();
            if(!isNew){
                sc = new Scanner(saveList);
                editor = new FileWriter(saveList, true);
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
}
