import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getElement(int i) {
        return this.tasks.get(i);
    }

    private static String getOrdinal(int n) {
        if (n >= 11 && n <= 13) {
            return n + "th";
        }
        switch (n % 10) {
            case 1:
                return n + "st of";
            case 2:
                return n + "nd of";
            case 3:
                return n + "rd of";
            default:
                return n + "th of";
        }
    }

    public Task add(String s) throws DukeException {
        String[] arr = s.split(" ");
        String taskType = arr[0];
        String taskDes = "";
        String taskTime = "";

        for (int i = 1; i < arr.length; i++) {//get task description
            if (arr[i].length() >= 1 && arr[i].charAt(0) == '/') {
                for (int j = i + 1; j < arr.length; j++) { //get task time
                    taskTime += " " + arr[j];
                }
                break;
            } else {
                taskDes += " " + arr[i];
            }
        }
        taskDes = taskDes.trim();
        taskTime = taskTime.trim();
        //handle exceptions
        if (!(taskType.equals("todo") || taskType.equals("deadline") || taskType.equals("event"))) {
            throw new DukeException("    ____________________________________________________________\n     " +
                    "\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(" +
                    "\n    ____________________________________________________________\n");
        }
        if (taskDes.equals("")) throw new DukeException(
                "    ____________________________________________________________\n     " +
                        "\u2639" + " OOPS!!! The description of a " + taskType + " cannot be empty." +
                        "\n    ____________________________________________________________\n");
        if ((taskType.equals("deadline") || taskType.equals("event")) && taskTime.equals(""))
            throw new DukeException(
                    "    ____________________________________________________________\n     " +
                            "\u2639" + " OOPS!!! The time of a " + taskType + " cannot be empty." +
                            "\n    ____________________________________________________________\n");

        try {
            Date date = new SimpleDateFormat("d/MM/yyyy HHmm").parse(taskTime);
            SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy, hh:mm a");
            taskTime = formatter.format(date);
            String[] array = taskTime.split(" ");
            array[0] = getOrdinal(Integer.valueOf(array[0]));
            array[array.length - 1] = array[array.length - 1].toLowerCase();
            taskTime = "";
            for (int i = 0; i < array.length; i++) {
                taskTime += " " + array[i];
            }
            taskTime = taskTime.trim();
        } catch (ParseException e) {

        }
        Task task;
        if (taskType.equals("todo")) {
            task = new ToDo(taskDes);
            tasks.add(task);
            return task;
        } else if (taskType.equals("deadline")) {
            task = new DeadLine(taskDes, taskTime);
            tasks.add(task);
            return task;
        } else if (taskType.equals("event")) {
            task = new Event(taskDes, taskTime);
            tasks.add(task);
            return task;
        } else {
            return null;
        }
    }


    public Task done(int n) {
        Task task = this.tasks.get(n - 1);
        task.mark(); //mark the corresponding task as done
        return task;

    }

    public Task delete(int n) {
        Task task = this.tasks.get(n - 1);
        this.tasks.remove(task); //delete task from list
        return task;

    }

    public String generateInfo() {
        //convert Task into a String which can be stored in the data file
        String taskFile = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String current;
            if (task.getTime().equals("")) {
                current = task.getLabel() + " | " + task.getStatus() + " | " + task.getDescription();
            } else {
                current = task.getLabel() + " | " + task.getStatus() + " | " + task.getDescription() + " | " + task.getTime();
            }
            if (i != tasks.size() - 1) {
                taskFile += current + System.lineSeparator();
            } else {
                taskFile += current;
            }
        }

        return taskFile;
    }

}
