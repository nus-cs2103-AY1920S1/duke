package duke.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import duke.command.TaskList;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.DukeException;

public class Storage {

    private File file;
    TaskList taskList;
    ArrayList<Task> list;

    public Storage(String filePath) {
        this.file = new File(filePath);
        this.list = new ArrayList<Task>();
    }

    public ArrayList<Task> load() throws DukeException, FileNotFoundException, ParseException, IOException {
        if (this.file.exists()) {
            Scanner scannerTask = new Scanner(this.file);
            while (scannerTask.hasNext()) {
                String taskText = scannerTask.nextLine();
                parseTextToTask(taskText, this.list);
            }
            return this.list;
        } else {
            file.createNewFile();
            throw (new DukeException("File not found"));
        }
    }

    public void setList(TaskList taskList) {
        this.list = taskList.getList();
    }

    public void parseTextToTask(String taskText, ArrayList<Task> list) throws ParseException {
        if (taskText.substring(0, 1).equals("T")) {
            Task task = new Todo(taskText.substring(8));
            if (taskText.substring(3,4).equals("1")) {
                task.markAsDone();
            }
            this.list.add(task);
        } else if (taskText.substring(0, 1).equals("D")) {
            String descriptionAndTime = taskText.substring(8);
            int index = descriptionAndTime.indexOf('|');
            String description = descriptionAndTime.substring(0, index - 1);
            String time = descriptionAndTime.substring(index + 2);
            Task task = new Deadline(description, time);
            if (taskText.substring(4,5).equals("1")) {
                task.markAsDone();
            }
            this.list.add(task);
        } else if (taskText.substring(0, 1).equals("E")) {
            String descriptionAndTime = taskText.substring(8);
            int index = descriptionAndTime.indexOf('|');
            String description = descriptionAndTime.substring(0, index - 1);
            String time = descriptionAndTime.substring(index + 2);
            Task task = new Event(description, time);
            if (taskText.substring(4,5).equals("1")) {
                task.markAsDone();
            }
            this.list.add(task);
        }
    }

    public void appendToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(this.file, true);
        String isDone = task.isDone() ? "1" : "0";
        String text = task.getTypeOfTask() + " | " + isDone + " | " + task.getDescription();
        if (task.getTypeOfTask().equals("D")) {
            text = text + " | " + ((Deadline)task).getTime() + System.lineSeparator();
        } else if (task.getTypeOfTask().equals("E")) {
            text = text + " | " + ((Event)task).getTime() + System.lineSeparator();
        } else {
            text = text + System.lineSeparator();
        }
        fw.write(text);
        fw.close();
    }

    public void updateFile() throws IOException {
        FileWriter writer = new FileWriter(this.file, false);
        String text = "";
        for(Task task: this.list) {
            String isDone = task.isDone() ? "1" : "0";
            text = text + task.getTypeOfTask() + " | " + isDone + " | " + task.getDescription();
            if (task.getTypeOfTask().equals("D")) {
                text = text + " | " + ((Deadline)task).getTime() + System.lineSeparator();
            } else if (task.getTypeOfTask().equals("E")) {
                text = text + " | " + ((Event)task).getTime() + System.lineSeparator();
            } else {
                text = text + System.lineSeparator();
            }
        }
        writer.write(text);
        writer.close();
    }
}
