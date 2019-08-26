import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> load() throws DukeException {
        ArrayList<String> fileList = new ArrayList<String>();
        try {
            File file = new File(this.filePath);
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                fileList.add(sc.nextLine());
            }

            return fileList;

        } catch (FileNotFoundException e) {
            throw new DukeException("Sorry! The file cannot be found!");
        }

    }

    public void save(ArrayList<Task> taskList) {

        try {
            FileWriter fw = new FileWriter(this.filePath);

            for (int i = 0; i < taskList.size(); i++) {
                fw.write(taskList.get(i).toFileFormat());
            }

            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong writing to file!");
        }
    }
}
