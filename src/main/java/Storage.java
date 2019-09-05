package duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException, ParseException {
        ArrayList<Task> list = new ArrayList<Task>();
        File file = new File(filePath);
        Scanner fs = new Scanner(file);
        while (fs.hasNext()) {
            String line = fs.nextLine();
            String[] taskArr = line.split(" \\| ");
            String type = taskArr[0];
            if (type.equals("T")) {
                Todo todo = new Todo(taskArr[2]);
                if (taskArr[1].equals("1")) {
                    todo.markAsDone();
                }
                list.add(todo);
            } else if (type.equals("D")) {
                String date = taskArr[3].substring(8, 10) + " " + taskArr[3].substring(4, 7) + " "
                        + taskArr[3].substring(24, 28) + " " + taskArr[3].substring(11, 16);
                Deadline deadline = new Deadline(taskArr[2], convertToDate(date));
                if (taskArr[1].equals("1")) {
                    deadline.markAsDone();
                }
                list.add(deadline);
            } else if (type.equals("E")) {
                String date = taskArr[3].substring(8, 10) + " " + taskArr[3].substring(4, 7) + " "
                        + taskArr[3].substring(24, 28) + " " + taskArr[3].substring(11, 16);
                Event event = new Event(taskArr[2], convertToDate(date));
                if (taskArr[1].equals("1")) {
                    event.markAsDone();
                }
                list.add(event);
            }
        }
        fs.close();
        return list;
    }

    public Date convertToDate(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy hh:mm");
        Date date = sdf.parse(str);
        return date;
    }

    private void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    private String convertTaskToFileFormat(Task task) {
        StringBuffer textToAdd = new StringBuffer();
        String type = task.getType();
        if (type.equals("T")) {
            textToAdd.append("T | ");
        } else if (type.equals("D")) {
            textToAdd.append("D | ");
        } else if (type.equals("E")) {
            textToAdd.append("E | ");
        }
        textToAdd.append(task.getStatusNum());
        textToAdd.append(" | ");
        textToAdd.append(task.getDescription());
        if (type.equals("D") || type.equals("E")) {
            textToAdd.append(" | ");
            textToAdd.append(task.getDate());
        }
        textToAdd.append("\n");
        return textToAdd.toString();
    }

    public void appendTaskToFile(Task task) throws IOException {
        String textToAppend = convertTaskToFileFormat(task);
        appendToFile(textToAppend);
    }

    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void updateTaskInFile(ArrayList<Task> list) throws IOException {
        StringBuffer textToAdd = new StringBuffer();
        for (Task task : list) {
            textToAdd.append(convertTaskToFileFormat(task));
        }
        writeToFile(textToAdd.toString());
    }
}