import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class TaskFileWriter {
    public void writeToFile(String filePath, ArrayList<Task> myList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : myList) {
            String description, textToAdd;
            Date date = null;
            textToAdd = "";
            description = task.getDescription();
            if (task instanceof Deadline) {
                textToAdd += "D";
                Deadline deadline = (Deadline) task;
                date = deadline.getBy();
            } else if (task instanceof Event) {
                textToAdd += "E";
                Event event = (Event) task;
                date = event.getAt();
            } else {
                textToAdd += "T";
            }
            if (task.getDoneIcon().equals("\u2713")) {
                textToAdd += " | Done";
            } else {
                textToAdd += " | Not done";
            }
            textToAdd += " | " + description;
            if (date != null) {
                textToAdd += " | " + date;
            }
//            textToAdd += "\n";
            fw.write(textToAdd + "\r\n");
        }

        fw.close();
    }

    public void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }
}
