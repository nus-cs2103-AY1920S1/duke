package duke.io;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.LinkedList;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private static String basePath = new File("").getAbsolutePath();
    private String filePath;

    /**
     * Constructor of Storage.
     * @param filePath relative path of list storing file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the assigned file and parse the stored list of tasks as <code>Task</code> objects.
     * @return Existing list of tasks
     * @throws IOException IOException
     */
    public LinkedList<Task> load() throws IOException {
        LinkedList<Task> tasks = new LinkedList<>();
        try {
            FileReader in = new FileReader(new File(basePath + filePath));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                String type = line.substring(line.indexOf(".") + 2, line.indexOf(".") + 3);
                String state = line.substring(line.indexOf(".") + 5, line.indexOf(".") + 6);
                String content = line.substring(line.indexOf(".") + 8);
                String time = "0";
                if (line.indexOf("(") > 0) {
                    content = line.substring(line.indexOf(".") + 8, line.indexOf("(") - 1);
                    time = line.substring(line.indexOf(":") + 2, line.indexOf(")"));
                }
                parseLine(type, state, content, time, tasks);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    private void parseLine(String type, String state, String content,
                           String time, LinkedList<Task> tasks) throws ParseException {
        switch (type) {
        case "T":
            Task todo = new ToDo(content);
            if (state.contentEquals("Y")) {
                todo.setDone();
            }
            tasks.add(todo);
            break;
        case "D":
            Task deadline = new Deadline(content, time);
            if (state.contentEquals("Y")) {
                deadline.setDone();
            }
            tasks.add(deadline);
            break;
        case "E":
            Task event = new Event(content, time);
            if (state.contentEquals("Y")) {
                event.setDone();
            }
            tasks.add(event);
            break;
        default:
            break;
        }
    }

    /**
     * Saves the tasks of <code>TaskList</code> in designated file.
     * @throws FileNotFoundException FileNotFoundException
     */
    public void saveData() throws FileNotFoundException {
        File file = new File(basePath + filePath);
        try (PrintWriter out = new PrintWriter(file)) {
            for (Task task : TaskList.taskList) {
                out.println(task.toString());
            }
        }
    }
}
