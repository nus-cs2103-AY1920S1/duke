import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

public class TaskList {
    private List<Task> tasks;
    private final String addedMessage = "Got it. I've added this task:";

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Lists items in To Do List.
     *
     * @return List as a string
     */
    public String list() {
        return padMessage(getTasks());
    }

    /**
     * Sets task at specified index of To Do List as done and returns it (one based numbering).
     *
     * @param i Index
     * @return Task at specified index.
     */
    public String done(int i) {
        tasks.get(i - 1).setDone(true);
        return tasks.get(i - 1).toString();
    }

    /**
     * Delete index of To Do List. (one based numbering)
     *
     * @param i Index
     * @return Deleted task
     */
    public String delete(int i) {
        String taskMessage = tasks.get(i - 1).toString();
        tasks.remove(i - 1);
        return taskMessage;
//        StringJoiner result = new StringJoiner("\n");
//        result.add(deleteMessage);
//        result.add(tasks.get(i - 1).toString());
//        tasks.remove(i - 1);
//        result.add(String.format("Now you have %d tasks in the list.", tasks.size()));
//        return padMessage(result.toString());
    }

    /**
     * Appends task to To Do List. (one based numbering)
     *
     * @param td ToDo
     * @return message to print
     */
    public String addToDo(ToDo td) {
        tasks.add(td);
        return tasks.get(tasks.size() - 1).toString();
    }

    /**
     * Appends and returns deadline to To Do List. (one based numbering).
     *
     * @param dl deadline
     * @return message to print
     */
    public String addDeadline(Deadline dl) {
        tasks.add(dl);
//        message = addedMessage();
//        try {
//            SimpleDateFormat readFormat = new SimpleDateFormat(format);
//            tasks.add(new Deadline(task, readFormat.parse(date)));
//            message = addedMessage();
//        } catch (ParseException pe) {
//            message = pe.getMessage();
//        }
        return tasks.get(tasks.size() - 1).toString();
    }

    /**
     * Appends and returns event to To Do List. (one based numbering)
     *
     * @param e Event
     * @return message to print
     */
    public String addEvent(Event e) {
        tasks.add(e);
        return tasks.get(tasks.size() - 1).toString();
    }

    /**
     * Saves tasks in specified path.
     *
     * @param filePath Path wherein text will be saved
     */
    public void save(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(getTasks());
        fw.close();
    }

    public int getTasksSize() {
        return tasks.size();
    }

    public String getTasks() {
        StringJoiner result = new StringJoiner("\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            result.add(String.format("%d.%s", i + 1, t));
        }

        return result.toString();
    }

    private String addedMessage() {
        StringJoiner result = new StringJoiner("\n");
        result.add(addedMessage);
        result.add(tasks.get(tasks.size() - 1).toString());
        result.add(String.format("Now you have %d tasks in the list.", tasks.size()));
        return padMessage(result.toString());
    }

    private String padMessage(String message) {
        StringJoiner result = new StringJoiner("\n");
        result.add("____________________________________________________________");
        result.add(message);
        result.add("____________________________________________________________");
        return result.toString();
    }
}