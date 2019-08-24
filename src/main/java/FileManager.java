import java.io.*;
import java.util.ArrayList;

/** This class is in charge of recording the current task list to the hard disk as well as reloading,
 * It saves the task list to the file dukedata/datafile.txt,
 * In this file, each line is a task.*/

public class FileManager {

    private static BufferedWriter recorder = null;
    private static BufferedReader reader = null;

    // Reload the previous task list.
    static ArrayList<Task> reload() throws IOException{
        reader = new BufferedReader(new FileReader("./dukedata/taskfile.txt"));
        ArrayList<Task> toReturn = new ArrayList<>();
        if (reader == null) return toReturn;
        String line = reader.readLine();
        while (line != null) {
            String[] lineComponents = line.split("\\|");
            boolean finished;
            if (lineComponents[1].equals("0")) finished = false;
            else finished = true;
            String taskname = lineComponents[2];
            if (lineComponents[0].equals("T")) {
                Task toAdd = new Todo(taskname);
                if (finished) toAdd.set_as_finish();
                toReturn.add(toAdd);
            } else if (lineComponents[0].equals("D")) {
                String tasktime = lineComponents[3];
                Task toAdd = new Deadline(taskname, tasktime);
                if (finished) toAdd.set_as_finish();
                toReturn.add(toAdd);
            } else {
                String tasktime = lineComponents[3];
                Task toAdd = new Event(taskname, tasktime);
                if (finished) toAdd.set_as_finish();
                toReturn.add(toAdd);
            }
            line = reader.readLine();
        }
        reader.close();
        return toReturn;
    }

    // Rewrite the task list file, remember to clear the original file first.
    static void rewrite(ArrayList<Task> tasklist) throws IOException{
        PrintWriter pw = new PrintWriter("./dukedata/taskfile.txt");
        pw.close();
        recorder = new BufferedWriter(new FileWriter("./dukedata/taskfile.txt", true));
        for (Task t : tasklist) {
            recorder.write(t.record_info());
            recorder.write("\n");
        }
        recorder.close();
    }
}
