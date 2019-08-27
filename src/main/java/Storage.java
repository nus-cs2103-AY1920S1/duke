import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }


    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> fileContent = new ArrayList<>();
        fileContent.add(new Task(null, "Task 0"));
        File file = new File(filePath);
        Scanner fileScan = new Scanner(file);
        while (fileScan.hasNext()) {
            String fileLine = fileScan.nextLine();
            String arr[] = fileLine.split(" \\| ");
            String type = arr[0];
            switch (type) {
                case "T":
                    fileContent.add(new Task(arr[0], arr[1], arr[2]));
                    break;
                case "D":
                    fileContent.add(new Deadline(arr[0], arr[1], arr[2], Parser.formatDate(arr[3])));
                    break;
                case "E":
                    fileContent.add(new Event(arr[0], arr[1], arr[2], Parser.formatDate(arr[3])));
                    break;
            }
        }

        return fileContent;
    }

    public void save(TaskList tasks) throws IOException {
        BufferedWriter fw = new BufferedWriter(new FileWriter(filePath));
        ArrayList<Task> list = tasks.getList();
        for (int i = 1; i < list.size(); i++) {
            fw.write(list.get(i).fileFormat());
        }
        fw.close();

    }
}
