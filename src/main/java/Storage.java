import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }

        File f = new File(filePath);
        f.createNewFile();
        Scanner s = new Scanner(f);
        ArrayList<Task> list = new ArrayList<Task>();

        try {
            readFileContents(filePath, list);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void readFileContents(String filePath, ArrayList<Task> list) throws FileNotFoundException, ParseException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            boolean checkDone;
            String currLine = s.nextLine();
            String[] currWords= currLine.split("[|]", 4);
            if (currWords[1].equals("1")) {
                checkDone = true;
            } else {
                checkDone = false;
            }

            if (currWords[0].equals("T")) {
                Task addedTask = new ToDos(currWords[2]);
                addedTask.setStatusIcon(checkDone);
                list.add(addedTask);
            } else if (currWords[0].equals("D")) {
                Task addedTask = new Deadline(currWords[2], currWords[3]);
                addedTask.setStatusIcon(checkDone);
                list.add(addedTask);
            } else if (currWords[0].equals("E")) {
                Task addedTask = new Event(currWords[2], currWords[3]);
                addedTask.setStatusIcon(checkDone);
                list.add(addedTask);
            }
        }
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public void save(TaskList taskList) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.list.size(); i++) {
            Task task = taskList.list.get(i);
            sb.append(task.format()).append("\n");
        }

        writeToFile("data/duke.txt", sb.toString());
    }
}
