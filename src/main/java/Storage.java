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

    public static Task createTaskFromFile (String s) throws DukeException{
        String[] command = s.split(" \\| ");
        boolean isPending = command[1].equals("1") ? true : false;
        switch (command[0]) {
        case "T":
            return new ToDos(command[2], isPending);
        
        case "D":
            return new Deadline(command[2], command[3], isPending);
        case "E":
            return new Event(command[2], command[3], isPending);
        default:
            throw new DukeException("Uncategorizable task.");
        }
    }

    public void addTaskToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(task.toStringForFile() + System.lineSeparator());
        fw.close();
    }

    public ArrayList<Task> load() throws FileNotFoundException, DukeException {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(this.filePath);
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            list.add(createTaskFromFile(scan.nextLine()));
        }
        scan.close();
        return list;
    }

    public void writeToFile(String filePath, ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : list) {
            fw.write(t.toStringForFile() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

}