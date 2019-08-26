import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(this.filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> oldList = new ArrayList<>();
        while (s.hasNextLine()) {
            String task = s.nextLine();
            String[] splitTask = task.split("|", 0);
            switch(splitTask[0]) {
                case "T":
                    ToDos todo = new ToDos(splitTask[2]);
                    todo.done = isDone(Integer.parseInt(splitTask[1]));
                    oldList.add(todo);
                    break;
                case "D":
                    Deadlines dl = new Deadlines(splitTask[2], splitTask[3]);
                    dl.done = isDone(Integer.parseInt(splitTask[1]));
                    oldList.add(dl);
                    break;
                case "E":
                    Events event = new Events(splitTask[2], splitTask[3]);
                    event.done = isDone(Integer.parseInt(splitTask[1]));
                    oldList.add(event);
                    break;
                default:
                    System.out.println("Task does not exist.");
                    break;
            }
        }
        return oldList;
    }

    public boolean isDone(int num) {
        if (num == 1) {
            return true;
        } else {
            return false;
        }
    }
}
