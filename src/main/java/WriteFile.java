import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WriteFile {

    private String dir;
    private String fileName;

    public WriteFile(String dir, String fileName) {
        this.dir = dir;
        this.fileName = fileName;
    }

    //get methods
    public String getPath() {
        return this.dir + this.fileName;
    }

    //set methods
    public void writeTaskToFile(ArrayList<Task> taskArr) {
        try {
            //create a new file or overwrite existing file
            File newFile = new File(this.getPath());
            FileWriter fw = new FileWriter(newFile);
            PrintWriter pw = new PrintWriter(fw);
            this.appendTaskToPrintWriter(pw, taskArr);
            pw.close();
        } catch (IOException err) {
            System.err.println(err);
        }
    }

    private void appendTaskToPrintWriter(PrintWriter pw, ArrayList<Task> taskArr) {
        //loop through the array and append each String to each line
        for (int x = 0; x < taskArr.size(); x++) {
            Task task = taskArr.get(x);
            String typeOfTask = String.valueOf(task.toString().charAt(1));
            String status = task.getStatusIcon() == "\u2713" ? "1" : "0";
            String desc = task.getDescription();

            pw.printf(typeOfTask + " | " + status + " | " + desc);

            //check if there is date exist
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                String taskDate = deadlineTask.getDate();
                pw.printf(" | " + taskDate);
            } else if (task instanceof Event) {
                Event eventTask = (Event) task;
                String taskDate = eventTask.getDate();
                pw.printf(" | " + taskDate);
            }
            pw.printf("%n");
        }
    }
}
