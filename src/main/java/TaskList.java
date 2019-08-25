import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> list = new ArrayList<Task>();

    public TaskList(ArrayList<String> content) throws DukeException {
        for (String s : content) {
            loadTask(s);
        }
    }

    public TaskList() {
    }

    public void loadTask(String s) throws DukeException {

            String[] strArr = s.split(" \\| ");
            String action = strArr[0];
            Task t = null;
            switch (action) {
                case "T":
                    t = new ToDo(strArr[2]);
                    break;
                case "D":
                    t = new Deadline(strArr[2], strArr[3]);
                    break;
                case "E":
                    t = new Event(strArr[2], strArr[3]);
                    break;
                default:
                    throw new DukeException("Previous Task storage is corrupted. Resetting your task . .");
            }
            if (strArr[1].equals("1")) {
                t.markAsDone();
            }
            list.add(t);
            System.out.println(s);
    }

    public void addTask(String s) throws DukeException {
        String[] strArr = s.split(" ");
        String action = strArr[0].toLowerCase();
        Task task = new Task("");

        if (action.equals("todo")) {
            if (s.length() < 6) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            String description = s.substring(5);
            task = new ToDo(description);

        } else if (action.equals("deadline")) {
            if (s.length() < 10) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            String[] temp = s.split("/by");
            if (temp.length < 2) {
                throw new DukeException("Please specify the deadline time using /by.");
            }
            String description = temp[0].substring(9).trim();
            String by = temp[1].trim();
            task = new Deadline(description, by);


        } else if (action.equals("event")) {
            if (s.length() < 7) {
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            String[] temp = s.split("/at");
            if (temp.length < 2) {
                throw new DukeException("Please specify the event time using /at.");
            }
            String description = temp[0].substring(6).trim();
            String at = temp[1].trim();
            task = new Event(description, at);

        } else {
            // Not an action
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        list.add(task);

    }
}
