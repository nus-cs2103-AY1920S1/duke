package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadFromFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>(100);
        try {
            File f = new File(this.filePath);
            if (!f.exists()) {
                f.getParentFile().mkdirs();
            } else {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String text;
                while ((text = br.readLine()) != null) {
                    String taskType = text.charAt(1) + "";
                    switch (taskType) {
                    case "T": {
                        Task todo = new Todo(text.substring(7));
                        if (text.substring(4, 5).equals("V")) {
                            todo.markedAsDone();
                        }
                        tasks.add(todo);
                        break;
                    }
                    case "D": {
                        Task deadline = new Deadline(text.substring(7, text.indexOf("by:") - 2),
                                fileTaskDateConverter(text.substring(text.indexOf("by:") + 4, text.length() - 1)));
                        if (text.substring(4, 5).equals("V")) {
                            deadline.markedAsDone();
                        }
                        tasks.add(deadline);
                        break;
                    }
                    case "E": {
                        Task event = new Event(text.substring(7, text.indexOf("at:") - 2),
                                fileTaskDateConverter(text.substring(text.indexOf("at:") + 4, text.length() - 1)));
                        if (text.substring(4, 5).equals("V")) {
                            event.markedAsDone();
                        }
                        tasks.add(event);
                        break;
                    }
                    default: {
                        throw new DukeException("Error occurred, invalid Task type found.");
                    }
                    }
                }
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        return tasks;
    }

    private Date fileTaskDateConverter(String date) throws DukeException {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd 'of' MMMM yyyy, hh:mm a");
            Date parseDate = formatter.parse(date);
            return parseDate;
        } catch (ParseException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }

    public void saveToFile(ArrayList<Task> listOfInputs) throws DukeException {
        try {
            File f = new File(this.filePath);
            f.getParentFile().mkdirs();
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            for (Task task : listOfInputs) {
                bw.append(task.toString());
                bw.append("\n");
            }
            bw.close();
        } catch (IOException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }
}