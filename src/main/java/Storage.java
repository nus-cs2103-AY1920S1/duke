import java.io.*;
import java.util.ArrayList;

public class Storage {
    File file;

    public Storage(String filepath) {
        this.file = new File(filepath);
    }

    public void saveToFile(String content) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(content);
        fw.close();
    }

    public ArrayList<Task> loadPreviousTasks() throws IOException, DukeException {
        ArrayList<Task> loadList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));

        String str = "";
        while ((str = br.readLine()) != null) {
            Task task = txtProcessor(str);
            loadList.add(task);
        }

        return loadList;
    }

    private Task txtProcessor(String line) {
        String details = line.substring(0,6);
        char[] detailsArray = details.toCharArray();
        String type = "" + detailsArray[1];
        String done = "" + detailsArray[4];
        boolean isDone = done.equals("+");
        String desc = line.substring(7);

        Task newTask = new Task("mummy");

        if (type.equals("T")) {
            newTask = new ToDo(desc);
        }

        if (type.equals("D")) {
            String[] dd = desc.split("by:");
            String deadline = dd[1].substring(0, dd[1].length() - 1);
            String description = dd[0].substring(0, dd[0].length() - 1);
            newTask = new Deadline(description, deadline);
        }

        if (type.equals("E")) {
            String[] dd = desc.split("at:");
            String deadline = dd[1].substring(0, dd[1].length() - 1);
            String description = dd[0].substring(0, dd[0].length() - 1);
            newTask = new Deadline(description, deadline);
        }

        if (isDone) {
            newTask.quietMarkAsDone();
        }

        return newTask;
    }
}