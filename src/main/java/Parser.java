import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class Parser {

    private Ui ui = new Ui();

    public TaskList parseDone(String str, TaskList list) throws Exception{
        String[] strArr = str.split(" ");

        if (strArr.length == 1) {
            throw new DukeException("OOPS!!! Please state the task number you want to mark as done.");
        }

        int index = Integer.parseInt(strArr[1]) - 1;

        if (index >= list.size()) {
            throw new DukeException("OOPS!!! The number is too large.");
        } else if (index < 0) {
            throw new DukeException("OOPS!!! The number is too small.");
        }

        Task t = list.get(index);
        t.markAsDone();

        ui.showTaskDone(t);

        return list;
    }

    public TaskList parseTodo(String str, TaskList list) throws Exception {
        String[] strArr = str.split(" ");
        if (strArr.length == 1) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }

        Task t = new Todo(str.substring(5));
        list.add(t);

        ui.showAddTask(t, list);

        return list;
    }

    public TaskList parseDeadline(String str, TaskList list) throws Exception {
        String[] strArr = str.split(" ");
        if (strArr.length == 1) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        if (!str.contains(" /by ")) {
            throw new DukeException("OOPS!!! The date/time of deadline cannot be empty.");
        }

        int indexOfSlash = str.indexOf(47);
        String ss1 = str.substring(0, indexOfSlash);
        String[] ss1Arr = ss1.split(" ");
        String ss2 = str.substring(indexOfSlash);
        String[] ss2Arr = ss2.split(" ");

        if (ss1Arr.length == 1) {
            throw new DukeException("OOPS!!! The description of deadline cannot be empty.");
        }
        if (ss2Arr.length == 1) {
            throw new DukeException("OOPS!!! The date/time of deadline cannot be empty.");
        }

        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
        Date date = parser.parse(ss2Arr[1]);

        int intTime = Integer.parseInt(ss2Arr[2]);
        int hour = intTime / 100;
        int minute = intTime % 100;
        LocalTime time = LocalTime.of(hour, minute);

        Task t = new Deadline(str.substring(9, indexOfSlash - 1), date, time);
        list.add(t);

        ui.showAddTask(t, list);

        return list;
    }

    public TaskList parseEvent(String str, TaskList list) throws Exception {
        String[] strArr = str.split(" ");
        if (strArr.length == 1) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        }
        if (!str.contains(" /at ")) {
            throw new DukeException("OOPS!!! The date/time of event cannot be empty.");
        }

        int indexOfSlash = str.indexOf(47);
        String ss1 = str.substring(0, indexOfSlash);
        String[] ss1Arr = ss1.split(" ");
        String ss2 = str.substring(indexOfSlash);
        String[] ss2Arr = ss2.split(" ");

        if (ss1Arr.length == 1) {
            throw new DukeException("OOPS!!! The description of event cannot be empty.");
        }
        if (ss2Arr.length == 1) {
            throw new DukeException("OOPS!!! The date/time of event cannot be empty.");
        }

        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
        Date date = parser.parse(ss2Arr[1]);

        int intTime = Integer.parseInt(ss2Arr[2]);
        int hour = intTime / 100;
        int minute = intTime % 100;
        LocalTime time = LocalTime.of(hour, minute);

        Task t = new Event(str.substring(6, indexOfSlash - 1), date, time);
        list.add(t);

        ui.showAddTask(t, list);

        return list;
    }

    public TaskList parseDelete(String str, TaskList list) throws Exception {
        String[] strArr = str.split(" ");

        if (strArr.length == 1) {
            throw new DukeException("OOPS!!! Please state the task number you want to delete.");
        }

        int index = Integer.parseInt(strArr[1]) - 1;

        if (index >= list.size()) {
            throw new DukeException("OOPS!!! The number is too large.");
        } else if (index < 0) {
            throw new DukeException("OOPS!!! The number is too small.");
        }

        Task t = list.delete(index);

        ui.showDeleteTask(t, list);

        return list;

    }
}
