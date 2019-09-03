import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    private String listString;
    private static String INDENT = "    ";

    public TaskList() {
        this.list = new ArrayList<>();
        this.listString = "";
    }

    public TaskList(ArrayList<Task> tasklist) {
        this.list = new ArrayList<>();
        this.listString = "";
        for(Task task : tasklist) {
            addTask(task);
        }
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public String getListString() {
        return listString;
    }

    public void addTask(Task newTask) {
        list.add(newTask);
        updateTodoString();
    }

    public void addDateTask(String details, String dateTime, String taskType) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTimeByDateTime = LocalDateTime.parse(dateTime, formatter);
        addTask(new Task(details, taskType, dateTimeByDateTime, false));
    }

    public void deleteTask(int index) {
        list.remove(index);
        updateTodoString();
    }

    public void markTaskAsDone(int index) {
        list.get(index).markAsDone();
        updateTodoString();
    }

    public void updateTodoString() {
        listString = "" + INDENT + " ";
        for (int i = 0; i < list.size(); i++) {
            listString += (i + 1) + ". " + list.get(i);
            if (i != (list.size() - 1)) {
                listString += '\n' + INDENT + ' ';
            }
        }
    }
}
