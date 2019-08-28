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

    public void create() throws IOException {
        Path path = Paths.get(this.filePath);
        try {
            Files.createFile(path);
        } catch (FileAlreadyExistsException ex) {
        }
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
            String isDone = ("" + current.charAt(4));
            String des = current.substring(8).trim();
            switch(type) {
                case 'T':
                    Todo newT = new Todo(des);
                    if (Boolean.parseBoolean(isDone)) {
                        newT.markAsDone();
                    }
                    list.add(newT);
                    break;

                case 'D':
                    int index = des.indexOf('|');
                    String taskName = des.substring(0, index).trim();
                    String date = des.substring(index + 1).trim();
                    Deadline newD = new Deadline(taskName, date);
                    if (Boolean.parseBoolean(isDone)) {
                        newD.markAsDone();
                    }
                    list.add(newD);
                    break;

                case 'E':
                    int index1 = des.indexOf('|');
                    String taskName1 = des.substring(0, index1).trim();
                    String date1 = des.substring(index1 + 1).trim();
                    Event newE = new Event(taskName1, date1);
                    if (Boolean.parseBoolean(isDone)) {
                        newE.markAsDone();
                    }
                    list.add(newE);
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
            FileWriter fileWriter = new FileWriter(this.filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Task task: taskList.list) {
                String taskData = task.getStatus();
                bufferedWriter.write(taskData);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}