import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class Storage {

    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public ArrayList<Task> load() throws IOException{
        ArrayList<Task> newTaskList = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            //load tasks into task list.
            String line;
            while ((line = br.readLine()) != null) {
                Task newTask = Parser.parseStringToTask(line);
                newTaskList.add(newTask);
            }
        } catch (ParseException e) {
            System.out.println("Failed to parse String to task");
        }

        return newTaskList;
    }

    public void save() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (Task task : TaskList.tasks) {
                String formattedData = formatData(task);

                writer.append(formattedData);
                writer.append("\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("invalid filePath");
        }
    }

    private String formatData(Task currTask) {
        String data = "";
        data += currTask.getType() + "|";
        data += currTask.getDone() ? "1|" : "0|";
        data += currTask.getDescription();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        DateTimeFormatter timeFormatter= DateTimeFormatter.ofPattern("HHmm", Locale.ENGLISH);

        if (currTask instanceof Deadline) {
            data += "|" + ((Deadline) currTask).getDate().format(dateFormatter) +
                    "|" + ((Deadline) currTask).getTime().format(timeFormatter);
        }
        if (currTask instanceof Event) {
            data += "|" + ((Event) currTask).getStartDate().format(dateFormatter) +
                    "|" + ((Event) currTask).getStartTime().format(timeFormatter) +
                    "|" + ((Event) currTask).getEndTime().format(timeFormatter);
        }
        return data;

    }

}

