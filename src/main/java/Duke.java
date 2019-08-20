import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> todo = new ArrayList<>();

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
                for (int i = 0; i < todo.size(); i++) {
                    System.out.println("     " + (i + 1) + ". " + todo.get(i));
                }
                System.out.println("    ____________________________________________________________");
            } else if (firstWord.equals("done")){
                int taskNum = Integer.parseInt(arr[1]);
                Task currTask = todo.get(taskNum-1);
                currTask.finishTask();
                System.out.println("    ____________________________________________________________");
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + currTask);
                System.out.println("    ____________________________________________________________");
            } else {
                System.out.println("    ____________________________________________________________");
                System.out.println("     added: " + cmd);
                System.out.println("    ____________________________________________________________");
                todo.add(new Task(cmd));
            }
            cmd = sc.nextLine();
        }

        System.out.println("   ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n");

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