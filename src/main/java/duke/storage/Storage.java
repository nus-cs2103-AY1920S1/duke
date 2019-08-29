package duke.storage;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.parser.Parser;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private SimpleDateFormat formatter;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
    }

    public String getFilePath() {
        return filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(" \\| ");
                switch (line[0]) {
                case "T":
                    Todo todo = new Todo(line[2]);
                    if (line[1].equals("1")) {
                        todo.markDone();
                        tasks.add(todo);
                    } else {
                        tasks.add(todo);
                    }
                    break;
                case "D":
                    Deadline deadline = new Deadline(line[2], Parser.parseDate(formatter, line[3]));
                    if (line[1].equals("1")) {
                        deadline.markDone();
                        tasks.add(deadline);
                    } else {
                        tasks.add(deadline);
                    }
                    break;
                case "E":
                    Event event = new Event(line[2], Parser.parseDate(formatter, line[3]));
                    if (line[1].equals("1")) {
                        event.markDone();
                        tasks.add(event);
                    } else {
                        tasks.add(event);
                    }
                    break;
                default:
                    break;
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("The file stated in the file path cannot be found.");
        }
    }

    public void write(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            boolean first = true;
            String textToAdd = "";

            for (Task task : taskList.getTaskList()) {
                if (first) {
                    textToAdd = task.formatToWrite();
                    first = false;
                } else {
                    textToAdd = String.format("%s\n%s", textToAdd, task.formatToWrite());
                }
            }
            System.out.print(textToAdd);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
