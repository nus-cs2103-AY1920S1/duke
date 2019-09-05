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

    public void save(ArrayList<Task> repeatList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : repeatList) {
            fw.write(t.printSave() + System.lineSeparator());
        }
        fw.close();
    }

    public ArrayList<Task> load() throws FileNotFoundException, DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        File dataFile = new File(filePath);
        Scanner sc = new Scanner(dataFile);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] lineSplit = line.split(" | ");
            try {
                switch (line.charAt(0)) {
                    case 'T':
                        taskList.add(new Todo(lineSplit[4]));
                        break;
                    case 'D':
                        taskList.add(new Deadline(lineSplit[4], lineSplit[6]));
                        break;
                    case 'E':
                        taskList.add(new Event(lineSplit[4], lineSplit[6]));
                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! Invalid data loaded.");
                }
                if (lineSplit[2].equals("1")) {
                    taskList.get(taskList.size() - 1).done();
                }
            } catch (IndexOutOfBoundsException ex) {
                throw new DukeException("☹ OOPS!!! Invalid data loaded.");
            }
        }
        return taskList;
    }
}
