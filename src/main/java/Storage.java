import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * This method is used to load the task list from disk.
     *
     * @return list of tasks
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> list = new ArrayList<>();
        Scanner sc1 = null;
        sc1 = new Scanner(file);

        while (sc1.hasNextLine()) {
            String[] oldList = sc1.nextLine().split(" / ");
            //System.out.println(Arrays.toString(oldList));
            Task t;
            if (oldList[0].trim().equals("T")) {
                t = new Todo(oldList[2].trim());
            } else {
                if (oldList[0].trim().equals("D")) {
                    t = new Deadline(oldList[2].trim(), oldList[3].trim());
                } else {
                    t = new Event(oldList[2].trim(), oldList[3].trim());
                }
            }
            if (oldList[1].trim().equals("1")) {
                t.markAsDone();
            }
            list.add(t);
        }
        return list;
    }

    /**
     * This method is used to update current items in the list.
     *
     * @param list the current task list
     * @return Nothing
     */
    public void updateList(ArrayList<Task> list) {
        //File file = new File("C:\\duke\\src\\main\\java\\data\\duke.txt");
        try (PrintWriter out = new PrintWriter(file)) {
            for (int i = 1; i <= list.size(); i++) {
                Task t = list.get(i - 1);
                if (t.symbol.equals("T")) {
                    out.println(t.symbol + " / " + (t.isDone ? 1 : 0) + " / " + t.getDescription());
                } else {
                    out.println(t.symbol + " / " + (t.isDone ? 1 : 0) + " / " + t.getDescription() + " / " + t.getExtraInfo());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
