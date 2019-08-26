import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private static String filePath;
    private ArrayList<Task> taskList;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> load() throws IOException {
        FileReader fReader = new FileReader(filePath);
        BufferedReader bReader = new BufferedReader(fReader);
        String line = null;

        while((line = bReader.readLine()) != null) {
            char taskType = line.charAt(1);
            char isDone = line.charAt(4);
            if (taskType == 'T') {
                String description = line.substring(7);
                Task todo = new ToDo(description);
                if (isDone == '✘') {
                    taskList.add(todo);
                } else {
                    todo.markAsDone();
                    taskList.add(todo);
                }
            } else if (taskType == 'D') {
                String date = "";
                String time = "";
                String description = line.substring(7, line.indexOf('(') - 1);
                String by = line.substring(line.indexOf(':') + 2, line.indexOf(')')).trim();
                String[] dateTimeSplit = by.split(" ");
                if (dateTimeSplit.length == 3) {
                    date = dateTimeSplit[0] + " " + dateTimeSplit[1] + " " + dateTimeSplit[2].substring(0, dateTimeSplit[2].length() - 1);
                    time = "";
                } else if (dateTimeSplit.length == 4) {
                    date = dateTimeSplit[0] + " " + dateTimeSplit[1] + " " + dateTimeSplit[2].substring(0, dateTimeSplit[2].length() - 1);
                    time = dateTimeSplit[3];
                }
                Task deadline = new Deadline(description, new DateTime(date, time));
                if (isDone == '✘') {
                    taskList.add(deadline);
                } else {
                    deadline.markAsDone();
                    taskList.add(deadline);
                }
            } else if (taskType == 'E') {
                String date = "";
                String time = "";
                String description = line.substring(7, line.indexOf('(') - 1);
                String at = line.substring(line.indexOf(':') + 2, line.indexOf(')')).trim();
                String[] dateTimeSplit = at.split(" ");
                if (dateTimeSplit.length == 3) {
                    date = dateTimeSplit[0] + " " + dateTimeSplit[1] + " " + dateTimeSplit[2].substring(0, dateTimeSplit[2].length() - 1);
                    time = "";
                } else if (dateTimeSplit.length == 4) {
                    date = dateTimeSplit[0] + " " + dateTimeSplit[1] + " " + dateTimeSplit[2].substring(0, dateTimeSplit[2].length() - 1);
                    time = dateTimeSplit[3];
                }
                Task event = new Event(description, new DateTime(date, time));
                if (isDone == '✘') {
                    taskList.add(event);
                } else {
                    event.markAsDone();
                    taskList.add(event);
                }
            }
        }
        bReader.close();
        return taskList;
    }

    public static void save() throws IOException {
        FileWriter fWriter = new FileWriter(filePath);
        BufferedWriter bWriter = new BufferedWriter(fWriter);

        for (Task task : TaskList.getCurrentTasks()) {
            bWriter.write(task + "\n");
        }
        bWriter.close();
    }
}
