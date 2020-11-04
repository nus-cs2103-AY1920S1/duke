import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Manages information by store and reading them.
 */

public class Storage {
    static final SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy HHmm");
    static final SimpleDateFormat dateFormat2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
    private String filePath;
    private TaskList taskList;

    public Storage(TaskList taskList, String filePath) {
        this.filePath = filePath;
        this.taskList = taskList;
    }

    /**
     * Reads information from a text file into the list.
     *
     * @throws DukeException throws IO related duke exception
     */
    public void readTask() throws DukeException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = null;

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(" \\| ");
                if (tokens.length < 3) {
                    throw new DukeException("Please enter date in this format: 2/12/2019 1800");
                }

                Task taskHolder = null;
                boolean isDone;
                String description = tokens[2];
                Date date = null;
                if (tokens[1].equals("1")) {
                    isDone = true;
                } else {
                    isDone = false;
                }
                if (tokens.length == 4) {
                    try {
                        date = dateFormat2.parse(tokens[3]);
                        dateFormat.format(date);
                    } catch (ParseException e) {
                        throw new DukeException("Please enter date in this format: 2/12/2019 1800");
                    }
                }

                switch (tokens[0]) {
                case "T":
                    taskHolder = new ToDo(description, isDone);
                    break;
                case "D":
                    taskHolder = new Deadline(description, date, isDone);
                    break;
                case "E":
                    taskHolder = new Event(description, date, isDone);
                    break;
                default:
                    throw new DukeException("Invalid task");
                }
                taskList.addTask(taskHolder);
            }

        } catch (FileNotFoundException e) {
            throw new DukeException("File not found");
        } catch (IOException e) {
            throw new DukeException("Error opening the file");
        }
    }

    /**
     * Saves all the current tasks in the list into a local text file.
     *
     * @throws DukeException throws IO related duke exception
     */
    public void saveTask() throws DukeException {
        PrintWriter pr = null;
        try {
            pr = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));

            sortTask();

            for (int j = 0; j < taskList.getSize(); j++) {

                int statusHolder = 0;

                if (taskList.getTask(j).getStatus()) {
                    statusHolder = 1;
                }

                String store = String.format("%s | %d | %s", taskList.getTask(j).getType(),
                    statusHolder, taskList.getTask(j).getDescription());
                if (taskList.getTask(j) instanceof Deadline) {
                    store += " | " + ((Deadline) taskList.getTask(j)).getBy();
                } else if (taskList.getTask(j) instanceof Event) {
                    store += " | " + ((Event) taskList.getTask(j)).getAt();
                }

                store += "\n";

                pr.write(store);

            }
        } catch (IOException e) {
            throw new DukeException("File could not be saved");
        } finally {
            if (pr != null) {
                pr.close();
            }
        }
    }

    private void sortTask() {
        List<Task> taskListTodo = new ArrayList<>();
        List<Task> taskListDeadline = new ArrayList<>();
        List<Task> taskListEvent = new ArrayList<>();
        for (int i = 0; i < taskList.getSize(); i++) {
            if (taskList.getTask(i) instanceof Deadline) {
                taskListDeadline.add(taskList.getTask(i));
            } else if (taskList.getTask(i) instanceof Event) {
                taskListEvent.add(taskList.getTask(i));
            } else {
                taskListTodo.add(taskList.getTask(i));
            }
        }

        taskList.clearTask();

        for (int j = 0; j < taskListTodo.size(); j++) {
            taskList.addTask(taskListTodo.get(j));
        }

        for (int j = 0; j < taskListDeadline.size(); j++) {
            taskList.addTask(taskListDeadline.get(j));
        }

        for (int j = 0; j < taskListEvent.size(); j++) {
            taskList.addTask(taskListEvent.get(j));
        }
        taskListTodo.clear();
        taskListDeadline.clear();
        taskListEvent.clear();
    }
}
