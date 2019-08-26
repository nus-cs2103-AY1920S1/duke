import java.io.*;
import java.util.ArrayList;

public class Storage {

    private static String filePath;
    private ArrayList<Task> store = new ArrayList<>();

    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    public Storage() {

    }

    public static String getFilePath() {
        return filePath;
    }

    public void createFolder() {
        String folderPath = "/Users/auxin/duke/data";
        File newFolder = new File(folderPath);
        if (newFolder.mkdir()) {
            System.out.println("Folder is created.");
        }
    }

    public void createFile() {
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("File is created to save tasks.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            File file = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String currLine;
            while ((currLine = br.readLine()) != null) {
                String[] arr = currLine.split(" - ");
                String type = arr[0];
                String isDone = arr[1];
                String action = arr[2];
                switch (type) {
                    case "T":
                        Task todo = new Todo(action);
                        if (isDone.equals("\u2713")) {
                            todo.markAsDone();
                        }
                        store.add(todo);
                        break;
                    case "D":
                        String by = arr[3];
                        Task deadline = new Deadline(action, by);
                        if (isDone.equals("\u2713")) {
                            deadline.markAsDone();
                        }
                        store.add(deadline);
                        break;
                    case "E":
                        String at = arr[3];
                        Task event = new Event(action, at);
                        if (isDone.equals("\u2713")) {
                            event.markAsDone();
                        }
                        store.add(event);
                        break;
                    default:
                        break;
                }
            }
            return store;
        } catch (IOException e) {
            throw new DukeException();
        }
    }

    public void appendToFile(String filePath, String textToAppend, boolean flag) {
        try {
            FileWriter fw = new FileWriter(filePath, flag);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(textToAppend + "\n");
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
