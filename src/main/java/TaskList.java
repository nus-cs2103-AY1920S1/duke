import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

public class TaskList {
    private List<Task> toDoList;
    private final String doneMessage = "Nice! I've marked this task as done:";
    private final String addedMessage = "Got it. I've added this task:";
    private final String deleteMessage = "Noted. I've removed this task: ";

    public TaskList() {
        toDoList = new ArrayList<>();
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
     * Set index of To Do List as done. (one based numbering)
     * 
     * @param i Index
     * @return Done message as a string
     */
    public String done(int i) {
        toDoList.get(i - 1).setDone(true);
        StringJoiner result = new StringJoiner("\n");
        result.add(doneMessage);
        result.add(toDoList.get(i - 1).toString());
        return padMessage(result.toString());
    }

    /**
     * Delete index of To Do List. (one based numbering)
     * 
     * @param i Index
     */
    public void delete(int i) {
        System.out.println("____________________________________________________________");
        System.out.println(deleteMessage);
        System.out.println(toDoList.get(i - 1));
        System.out.println(String.format("Now you have %d tasks in the list.", toDoList.size()));
        System.out.println("____________________________________________________________");
    }

    /**
     * Appends task to To Do List. (one based numbering)
     * 
     * @param task Task description
     */
    public void addToDo(String task) {
        toDoList.add(new ToDo(task));
        this.addedMessage();
    }

    /**
     * Appends deadline to To Do List. (one based numbering)
     * 
     * @param task Task description
     * @param date Deadline in date format
     */
    public void addDeadline(String task, Date date) {
        toDoList.add(new Deadline(task, date));
        this.addedMessage();
    }

    /**
     * Appends event to To Do List. (one based numbering)
     * 
     * @param task Task description
     * @param date Date
     */
    public void addEvent(String task, Date date) {
        toDoList.add(new Event(task, date));
        this.addedMessage();
    }

    /**
     * Saves tasks in specified path.
     * 
     * @param filePath  Path wherein text will be saved
     * @param textToAdd Text to add
     */
    public void save(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(getTasks());
        fw.close();
    }

    private String getTasks() {
        StringJoiner result = new StringJoiner("\n");
        for (int i = 0; i < toDoList.size(); i++) {
            Task t = toDoList.get(i);
            result.add(String.format("%d.%s", i + 1, t));
        }

        return result.toString();
    }

    private String addedMessage() {
        StringJoiner result = new StringJoiner("\n");
        result.add(addedMessage);
        result.add(toDoList.get(toDoList.size() - 1).toString());
        result.add(String.format("Now you have %d tasks in the list.", toDoList.size()));
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