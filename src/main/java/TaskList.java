import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {

    private static DateTimeFormatter dukeDateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private ArrayList<Task> dukeTaskList = null;

    public TaskList(ArrayList<Task> dukeTaskList) {
        this.dukeTaskList = dukeTaskList;
    }

    public TaskList() {
        this.dukeTaskList = new ArrayList<>();
    }

    public void addTask(Task toAdd) {
        dukeTaskList.add(toAdd);
    }

    public void deleteTask(Task toDelete) {
        dukeTaskList.remove(toDelete);
    }

    public Task getTask(int index) {
        return dukeTaskList.get(index);
    }

    public Task removeTask(int index) {
        return dukeTaskList.remove(index);
    }

    public ArrayList<Task> getDukeTaskList() {
        return dukeTaskList;
    }

    public int getSize() {
        return dukeTaskList.size();
    }

    public void addTaskAfterValidation(String dateTimeString, Task t) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, dukeDateTimeFormatter);

            if (t instanceof Event) {
                Event e = (Event) t;
                e.setDateTimeAt(dateTime);
            } else {
                Deadline d = (Deadline) t;
                d.setDateTimeBy(dateTime);
            }

            addTask(t);
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + dukeTaskList.size() + " tasks in the list.");
        } catch (Exception e) {
            System.out.println("Task not added to list because the input format for date and time is unrecognised. " +
                    "Please enter date and time in dd/MM/yyyy HHmm format.");
        }
    }

    public void displayFullList() {
        for (int i = 0; i < dukeTaskList.size(); i++) {
            int itemIndex = i + 1;
            String itemDisplay = itemIndex + "." + dukeTaskList.get(i).toString();
            System.out.println(itemDisplay);
        }
    }

}



