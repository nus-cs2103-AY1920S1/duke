import java.util.*;

public class Duke {

    private ArrayList<Task> _tasks;

    public Duke() {
        _tasks = new ArrayList<>();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);

        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");

        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {

            String arr[] = cmd.split(" ", 2);
            String firstWord = arr[0];

            if (firstWord.equals("list")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < _tasks.size(); i++) {
                    System.out.println("     " + (i + 1) + ". " + _tasks.get(i));
                }
                System.out.println("    ____________________________________________________________");
            } else if (firstWord.equals("done")) {
                int taskNum = Integer.parseInt(arr[1]);
                Task currTask = _tasks.get(taskNum - 1);
                currTask.finishTask();
                System.out.println("    ____________________________________________________________");
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + currTask);
                System.out.println("    ____________________________________________________________");
            } else {
                System.out.println("    ____________________________________________________________");
                System.out.println("     added: " + cmd);
                System.out.println("    ____________________________________________________________");
                _tasks.add(new Task(cmd));
            }
            cmd = sc.nextLine();
        }

        System.out.println("   ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n");

    }

    public static void main(String[] args) {
        Duke lvl4 = new Duke();
        lvl4.run();
    }
}

class Task {

    String _name;
    String _status;

    public Task(String name) {
        _name = name;
        _status = "✗";
    }

    public String toString() {  return "[" + _status + "] " + _name; }

    public void finishTask() {_status = "✓";}

}

class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }
    public String toString() { return "[T]" + super.toString(); }
}

class Deadline extends Task {
    String _dateTime;

    public Deadline(String name, String dateTime) {
        super(name);
        _dateTime = dateTime;
    }
    public String toString() {
        String arr[] = _dateTime.split(" ", 2);
        return "[D]" + super.toString() + "(" + arr[0] + ": " + arr[1] + ")";
    }
}

class Event extends Task {
    String _dateTime;

    public Event(String name, String dateTime) {
        super(name);
        _dateTime = dateTime;
    }
    public String toString() {
        String arr[] = _dateTime.split(" ", 2);
        return "[E]" + super.toString() + "(" + arr[0] + ": " + arr[1] + ")";
    }
}