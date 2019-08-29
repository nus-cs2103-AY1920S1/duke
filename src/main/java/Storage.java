import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage (String filePath){
        this.filePath = filePath;
    }

    public ArrayList<Task> loadData() throws DukeException {
        try {
            File f = new File(filePath);
            ArrayList<Task> list = new ArrayList<>();
            //System.out.println(f.getAbsolutePath());
            Scanner sc = new Scanner(f);

            while (sc.hasNext()) {
                String type = sc.next();
                switch (type) {
                case "T":
                    boolean done = sc.nextBoolean();
                    String taskName = sc.nextLine().trim();
                    list.add(new Task(taskName, done));
                    break;
                case "D":
                    done = sc.nextBoolean();
                    taskName = sc.nextLine().trim();
                    String[] userWords = taskName.split("/");
                    list.add(new Deadline(userWords[0], userWords[1], done));
                    break;
                case "E":
                    done = sc.nextBoolean();
                    taskName = sc.nextLine().trim();
                    userWords = taskName.split("/");
                    list.add(new Event(userWords[0], userWords[1], done));
                    break;
                }
            }
            return list;
        }catch(FileNotFoundException e){
            throw new DukeException("File not found. Generating empty list...");
        }
    }

    public void saveData(ArrayList<Task> list) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            String data = "";
            for (Task t : list) {
                data += t.writeFormat() + "\n";
            }
            fw.write(data);
            fw.close();
        }catch(IOException e){
            throw new DukeException("Error writing file.");
        }
    }
}
