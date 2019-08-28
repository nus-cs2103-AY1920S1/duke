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

    public ArrayList<Task> load() throws FileNotFoundException{
        ArrayList<Task> list = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] arr  = line.split("[|]");
            boolean isDone = !arr[1].trim().equals("0");
            Task task;
            if  (arr[0].trim().equals("T")) {
                task = new Todo(arr[2].trim(), isDone, "");
            } else if (arr[0].trim().equals("D")) {
                task = new Deadline(arr[2].trim(), isDone, arr[3].trim());
            } else {
                task = new Event(arr[2].trim(), isDone, arr[3].trim());
            }
            list.add(task);
        }
        return list;
    }

    public static  void  updateFile(ArrayList<Task> arr) {
        File dir = new File("/Users/joannasara/Desktop/duke/data");
        if (!dir.exists()) {
            dir.mkdir();
        }

        String  fileString = "";
        for (Task t : arr) {
            fileString += t.getFileStringFormat() + "\n";
        }

        try {
            File f = new File("data/tasks.txt");
            f.createNewFile();
            FileWriter fw = new FileWriter("data/tasks.txt");
            fw.write(fileString);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
