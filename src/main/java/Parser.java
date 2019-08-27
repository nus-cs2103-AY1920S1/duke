import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class Parser {

    private Ui ui = new Ui();

    public TaskList parseDone(String str, TaskList tasks) throws Exception{
        String[] strArr = str.split(" ");

        if (strArr.length == 1) { //no description
            throw new DukeException("OOPS!!! Please state the task number you want to mark as done.");
        }

        int index = Integer.parseInt(strArr[1]) - 1;

        if (index >= tasks.size()) {
            throw new DukeException("OOPS!!! The number is too large.");
        } else if (index < 0) {
            throw new DukeException("OOPS!!! The number is too small.");
        }

        Task t = tasks.get(index);
        t.markAsDone();

        ui.showTaskDone(t);

        return tasks;
    }

    public TaskList parseTodo(String str, TaskList tasks) throws Exception {
        String[] strArr = str.split(" ");
        if (strArr.length == 1) { //no description
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }

        Task t = new Todo(str.substring(5));
        tasks.add(t);

        ui.showAddTask(t, tasks);

        return tasks;
    }

    public TaskList parseDeadline(String str, TaskList tasks) throws Exception {
        String[] strArr = str.split(" ");
        if (strArr.length == 1) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        if (!str.contains(" /by ")) {
            throw new DukeException("OOPS!!! The date/time of deadline cannot be empty.");
        }

        int indexOfSlash = str.indexOf(47);
        String ss1 = str.substring(0, indexOfSlash); //description
        String[] ss1Arr = ss1.split(" ");
        String ss2 = str.substring(indexOfSlash); //date and time
        String[] ss2Arr = ss2.split(" ");

        if (ss1Arr.length == 1) { //no description
            throw new DukeException("OOPS!!! The description of deadline cannot be empty.");
        }
        if (ss2Arr.length < 3) { // no date or no time
            throw new DukeException("OOPS!!! The date/time of deadline cannot be empty.");
        }

        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
        Date date = parser.parse(ss2Arr[1]);

        int intTime = Integer.parseInt(ss2Arr[2]);
        int hour = intTime / 100;
        int minute = intTime % 100;
        LocalTime time = LocalTime.of(hour, minute);

        Task t = new Deadline(str.substring(9, indexOfSlash - 1), date, time);
        tasks.add(t);

        ui.showAddTask(t, tasks);

        return tasks;
    }

    public TaskList parseEvent(String str, TaskList tasks) throws Exception {
        String[] strArr = str.split(" ");
        if (strArr.length == 1) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        }
        if (!str.contains(" /at ")) {
            throw new DukeException("OOPS!!! The date/time of event cannot be empty.");
        }

        int indexOfSlash = str.indexOf(47);
        String ss1 = str.substring(0, indexOfSlash);
        String[] ss1Arr = ss1.split(" "); //description
        String ss2 = str.substring(indexOfSlash);
        String[] ss2Arr = ss2.split(" "); //date and time

        if (ss1Arr.length == 1) { //no description
            throw new DukeException("OOPS!!! The description of event cannot be empty.");
        }
        if (ss2Arr.length < 3) { //no date or no time
            throw new DukeException("OOPS!!! The date/time of event cannot be empty.");
        }

        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
        Date date = parser.parse(ss2Arr[1]);

        int intTime = Integer.parseInt(ss2Arr[2]);
        int hour = intTime / 100;
        int minute = intTime % 100;
        LocalTime time = LocalTime.of(hour, minute);

        Task t = new Event(str.substring(6, indexOfSlash - 1), date, time);
        tasks.add(t);

        ui.showAddTask(t, tasks);

        return tasks;
    }

    public TaskList parseDelete(String str, TaskList tasks) throws Exception {
        String[] strArr = str.split(" ");

        if (strArr.length == 1) {
            throw new DukeException("OOPS!!! Please state the task number you want to delete.");
        }

        int index = Integer.parseInt(strArr[1]) - 1;

        if (index >= tasks.size()) {
            throw new DukeException("OOPS!!! The number is too large.");
        } else if (index < 0) {
            throw new DukeException("OOPS!!! The number is too small.");
        }

        Task t = tasks.delete(index);

        ui.showDeleteTask(t, tasks);

        return tasks;

    }
}
