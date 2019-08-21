import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> commands = new ArrayList<Task>();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] inputArr = input.split(" ");
            String command = inputArr[0];
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scanner.close();
                break;
            } else if(command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                int count = 1;
                for (Task s : commands) {
                    System.out.println(count + ". " + s);
                    count++;
                }
            } else if (command.equals("done")) {
                int index = Integer.parseInt(inputArr[1]) - 1;
                Task doneTask = commands.get(index);
                doneTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  " + doneTask);
            } else if (command.equals("deadline")) {
                String dL = input.split(" ", 2)[1];
                String[] taskDeadLine = dL.split(" /by ");
                String taskD = taskDeadLine[0];
                String by = taskDeadLine[1];
                Task tt = new Deadline(taskD, by);
                commands.add(tt);
                System.out.println("Got it. I've added this task:\n  " + tt
                                   + "\nNow you have " + commands.size() + " tasks in the list.");
            } else if(command.equals("event")) {
                String eEvent = input.split(" ", 2)[1];
                String[] taskEvent = eEvent.split(" /at ");
                String taskE = taskEvent[0];
                String at = taskEvent[1];
                Task ee = new Event(taskE, at);
                commands.add(ee);
                System.out.println("Got it. I've added this task:\n  " + ee
                                   + "\nNow you have " + commands.size() + " tasks in the list.");
            } else {
                String todoT = input.split(" ", 2)[1];
                Task t = new Todo(todoT);
                commands.add(t);
                System.out.println("Got it. I've added this task:\n  " + t
                        + "\nNow you have " + commands.size() + " tasks in the list.");
            }
        }
    }
}
