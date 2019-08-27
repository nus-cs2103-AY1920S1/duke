import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Storage {

    String filePath;
    SimpleDateFormat formatter;

    public Storage(String filePath, SimpleDateFormat formatter) {
        this.filePath = filePath;
        this.formatter = formatter;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(this.filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> oldList = new ArrayList<>();
        while (s.hasNextLine()) {
            String task = s.nextLine();
            String[] splitTask = task.split("\\|");
            switch(splitTask[0]) {
                case "T":
                    ToDos todo = new ToDos(splitTask[2], formatter);
                    todo.done = isDone(Integer.parseInt(splitTask[1]));
                    oldList.add(todo);
                    break;
                case "D":
                    try {
                        Date date = formatter.parse(splitTask[3]);
                        Deadlines dl = new Deadlines(splitTask[2], formatter, date);
                        dl.done = isDone(Integer.parseInt(splitTask[1]));
                        oldList.add(dl);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "E":
                    try {
                        System.out.println(splitTask[3]);
                        Date date = formatter.parse(splitTask[3]);
                        Events event = new Events(splitTask[2], formatter, date);
                        event.done = isDone(Integer.parseInt(splitTask[1]));
                        oldList.add(event);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Task does not exist.");
                    break;
            }
        }
        return oldList;
    }

    public void save(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
    public boolean isDone(int num) {
        if (num == 1) {
            return true;
        } else {
            return false;
        }
    }
}
