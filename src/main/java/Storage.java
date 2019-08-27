import java.io.*;
import java.util.ArrayList;

public class Storage {
    public ArrayList<Task> tasks;

    public Storage(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void readData() {
        try {
            File file = new File("C:\\Users\\hooncp\\Desktop\\duke\\data\\data.txt");
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;
            while ((line = in.readLine()) != null) {
                String regex = " " +  "\\|"  + " ";
                String[] data = line.split(regex);
                Task t;
                if (data[0].equals("T")) {
                    t = new Todo(data[2], changeStringToBoolean(data[1]));
                } else if (data[0].equals("D")) {
                    t = new Deadline(data[2], data[3], changeStringToBoolean(data[1]));
                } else {
                    t = new Event(data[2], data[3], changeStringToBoolean(data[1]));
                }
                    tasks.add(t);
            }
            in.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean changeStringToBoolean(String number) {
        return (number.equals("1"));
    }

    public void rewriteData() {
        try {
            FileWriter fw = new FileWriter("C:\\Users\\hooncp\\Desktop\\duke\\data\\data.txt");
            for (Task t : tasks) {
                fw.write(t.toDataString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
