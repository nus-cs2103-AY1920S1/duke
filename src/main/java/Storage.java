import java.io.*;
import java.util.ArrayList;

public class Storage {
    public ArrayList<Task> tasks = null;
    public void save(ArrayList<Task> tasks) throws Exception{
        int size = tasks.size();
        //may have to catch error if no items in list
        StringBuilder listOfTask = new StringBuilder();
        for (int i = 0; i < size; i++) {
            listOfTask.append(i+1+". " +tasks.get(i)+"\n" + "     ");
        }
        PrintWriter writer = new PrintWriter(new FileOutputStream("list.txt", false));
        writer.print("     " + listOfTask);
        writer.close();

        FileOutputStream fos = new FileOutputStream("t.tmp", false);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(tasks);
        oos.close();
    }
    public void load(ArrayList<Task> tasks) throws Exception{
        FileInputStream fis = new FileInputStream("t.tmp");
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<Task> temp = (ArrayList<Task>) ois.readObject();
        for (Task task : temp) {
            tasks.add(task);
        }
        ois.close();
    }
}
