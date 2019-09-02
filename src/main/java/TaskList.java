import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Encapsulates a task list.
 * A task list has a date time formatter and also and ArrayList
 * to hold tasks, named dukeTaskList.
 */
public class TaskList {

    private static DateTimeFormatter dukeDateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private ArrayList<Task> dukeTaskList = null;

    /**
     * Constructor.
     * @param dukeTaskList ArrayList that holds all the tasks
     */
    public TaskList(ArrayList<Task> dukeTaskList) {
        this.dukeTaskList = dukeTaskList;
    }

    /**
     * Overloaded constructor.
     */
    public TaskList() {
        this.dukeTaskList = new ArrayList<>();
    }

    /**
     * Adds a task to task list.
     * @param toAdd task to be added
     */
    public void addTask(Task toAdd) {
        dukeTaskList.add(toAdd);
    }

    /**
     * Deletes a particular task from dukeTaskList
     * @param toDelete task to be deleted
     */
    public void deleteTask(Task toDelete) {
        dukeTaskList.remove(toDelete);
    }

    /**
     * Returns a task of a particular index position in dukeTaskList
     * @param index index position of the task to be retrieved
     * @return task at the input index position
     */
    public Task getTask(int index) {
        return dukeTaskList.get(index);
    }

    /**
     * Removes and returns a task of the input index position from dukeTaskList
     * @param index index position of the task to be removed
     * @return removed task
     */
    public Task removeTask(int index) {
        return dukeTaskList.remove(index);
    }

    /**
     * Returns dukeTaskList, the ArrayList that holds all the tasks.
     * @return dukeTaskList
     */
    public ArrayList<Task> getDukeTaskList() {
        return dukeTaskList;
    }

    /**
     * Returns the size of dukeTaskList.
     * @return
     */
    public int getSize() {
        return dukeTaskList.size();
    }

    /**
     * Adds task into dukeTaskList upon successful validation.
     * @param dateTimeString user input string format for date and time of task
     * @param t task to be added after successful validation
     */
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
            System.out.println("Task not added to list because the input format for date and time is unrecognised. "
                    + "Please enter date and time in dd/MM/yyyy HHmm format.");
        }
    }

    public String addTaskAfterValidationForGui(String dateTimeString, Task t) {
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
            StringBuilder sb = new StringBuilder();
            sb.append("Got it. I've added this task:");
            sb.append("\n");
            sb.append(t);
            sb.append("\n");
            sb.append("Now you have " + dukeTaskList.size() + " tasks in the list.");
            return sb.toString();
        } catch (Exception e) {
            return "Task not added to list because the input format for date and time is unrecognised. "
                    + "Please enter date and time in dd/MM/yyyy HHmm format.";
        }
    }

    /**
     * Prints the full list of tasks.
     */
    public void displayFullList() {
        for (int i = 0; i < dukeTaskList.size(); i++) {
            int itemIndex = i + 1;
            String itemDisplay = itemIndex + "." + dukeTaskList.get(i).toString();
            System.out.println(itemDisplay);
        }
    }

    public String displayFullListForGui() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dukeTaskList.size(); i++) {
            int itemIndex = i + 1;
            String itemDisplay = itemIndex + "." + dukeTaskList.get(i).toString();
            sb.append(itemDisplay);
            sb.append("\n");
        }
        return sb.toString();
    }

    public ArrayList<Task> generateMatchingList(String keyword) {
        ArrayList<Task> outputList = new ArrayList<>();
        for (int i = 0; i < dukeTaskList.size(); i++) {
            if (dukeTaskList.get(i).toString().contains(keyword)) {
                outputList.add(dukeTaskList.get(i));
            }
        }
        return outputList;
    }

}



