import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasklist;
    protected Storage storage;

    public TaskList(Storage storage) {
        this.tasklist = new ArrayList<Task>();
        this.storage = storage;
    }

    public String addTask(String s) {
        String[] task = s.split(" ");
        String statement = "";
        String[] modifiedTask = Parser.processTask(task);
        switch (modifiedTask[0]) {
        case "todo":
            ToDo todo = new ToDo(modifiedTask[1]);
            tasklist.add(todo);
            statement = "Got it. I've added this task:\n  " + todo + "\nNow you have " + Task.getTotal()
                            + " tasks in the list.";
            storage.save(tasklist);
            break;
        case "deadline":
            Deadline deadline = new Deadline(modifiedTask[1], modifiedTask[2]);
            tasklist.add(deadline);
            statement = "Got it. I've added this task:\n  " + deadline + "\nNow you have " + Task.getTotal()
                            + " tasks in the list.";
            storage.save(tasklist);
            break;
        case "event":
            Event event = new Event(modifiedTask[1], modifiedTask[2]);
            tasklist.add(event);
            statement = "Got it. I've added this task:\n  " + event + "\nNow you have " + Task.getTotal()
                            + " tasks in the list.";
            storage.save(tasklist);
            break;
        case "list":
            statement =  checkList();
            break;
        case "done":
            statement = complete(Integer.parseInt(modifiedTask[1]));
            storage.save(tasklist);
            break;
            case "delete":
            statement = delete(Integer.parseInt(modifiedTask[1]));
            storage.save(tasklist);
            break;
        case "bye":
            statement = "bye";
            break;
        default:
            statement = modifiedTask[0];
            break;
        }
        return statement;
    }

    protected String checkList() {
        if (this.tasklist.isEmpty()) {
            return "\u2639 OOPS!!! There are no tasks in your list.";
        }
        String s = "Here are the tasks in your list:\n";
        for (int i = 0; i < this.tasklist.size(); i++) {
            Task t = this.tasklist.get(i);
           s += ((i+1) + "." + t + "\n");
        }
        return s.substring(0, s.length() - 1);
    }

    protected String complete(int i) {
        Task t = this.tasklist.get(i - 1);
        t.setDone();
        String statement = "Nice! I've marked this task as done:\n  " + t;
        return statement;
    }

    protected String delete(int i) {
        Task t = this.tasklist.remove(i - 1);
        Task.decTotal();
        String statement = "Noted. I've removed this task:\n  " + t + "\nNow you have " + Task.getTotal()
                            + " tasks in the list.";
        return statement;
    }

}
