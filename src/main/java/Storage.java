import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String file;

    public Storage(String file) {
        this.file = file;
    }

    public ArrayList<Task> load()  {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File f = new File(file); // create a File for the given file path
            Scanner sc = new Scanner(f); // create a Scanner using the File as the source
            Task t;
            while (sc.hasNext()) {
                String next = sc.nextLine();
                String[] arr = next.split("@");
                boolean isDone = arr[1].equals("1");
                String description = arr[2];
                if (arr[0].contains("T")) {
                    t = new Todos(description.trim(), isDone);
                } else if (arr[0].contains("D")) {
                    t = new Deadline(description.trim(), isDone, arr[3].trim());
                } else {
                    t = new Event(description.trim(), isDone, arr[3].trim());
                }

                list.add(t);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (ParseException e) {
            System.out.println("Date in wrong format");
        } finally {
            return list;
        }
    }

    public void writeFile(ArrayList<Task> list) {
        try {
            FileWriter fw = new FileWriter(file);
            String textToAdd = "";
            for (Task task : list) {
                textToAdd += task.print() + "\n";
            }
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }
}
