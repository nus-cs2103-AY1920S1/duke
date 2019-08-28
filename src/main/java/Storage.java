import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {

    public String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    //load file contents into arraylist
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> list = new ArrayList<>();
        try {
        File currentFile = new File(this.filePath);
        Scanner sc = new Scanner(currentFile);
        while(sc.hasNext()) {
            String current = sc.nextLine();
            char type = current.charAt(0);
            int isDone = Character.getNumericValue(current.charAt(4));
            String description = current.substring(8).trim();
            switch(type) {
                case 'T':
                    Todo newTask = new Todo(description);
                    if (isDone == 1) {
                        newTask.markAsDone();
                    }
                    list.add(newTask);
                    break;

                case 'D':
                    int index = description.indexOf('|');
                    String deadlineName = description.substring(0, index);
                    String by = description.substring(index + 1).trim();
                    Deadline newDeadline = new Deadline(deadlineName, by.trim());
                    if (isDone == 1) {
                        newDeadline.markAsDone();
                    }
                    list.add(newDeadline);
                    break;

                case 'E':
                    int index1 = description.indexOf('|');
                    String eventName = description.substring(0, index1).trim();
                    String at = description.substring(index1 + 1).trim();
                    Event newEvent = new Event(eventName, at.trim());
                    if (isDone == 1) {
                        newEvent.markAsDone();
                    }
                    list.add(newEvent);
                    break;

                default:
            }
        }
       
        } catch (FileNotFoundException err) {
            System.out.println(err.getMessage());
        } finally {
            return list;
        }
        
    }

    //write arraylist contents to file
    public void write(TaskList taskList) {
        try {
            BufferedWriter bWrite = new BufferedWriter(new FileWriter(this.filePath));
            for (Task task: taskList.list) {
                String taskData = task.getStatus();
                bWrite.write(taskData);
                bWrite.newLine();
            }
            bWrite.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}