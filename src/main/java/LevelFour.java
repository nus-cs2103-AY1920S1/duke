import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelFour {
    private List<Task> list;
    // Main function
    public void run() {
        greet();
        list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String input = sc.next(); // Initial Input
        while(!input.equals("bye")) {
            switch (input) {
                case "list":
                    echoList(list);
                    break;
                case "done":
                    completeTask(sc.nextInt());
                    break;
                case "todo":
                    Task newTodo = new Todo(sc.nextLine());
                    list.add(newTodo);
                    echoEntry(newTodo.toString());
                    break;
                case "deadline":
                    // Parse deadline
                    String[] parsedDeadline = sc.nextLine().split(" \\/by ");
                    Task newDeadline = new Deadline(parsedDeadline[0], parsedDeadline[1]);
                    list.add(newDeadline);
                    echoEntry(newDeadline.toString());
                    break;
                case "event":
                    String[] parsedEvent = sc.nextLine().split(" \\/at ");
                    Task newEvent = new Event(parsedEvent[0], parsedEvent[1]);
                    list.add(newEvent);
                    echoEntry(newEvent.toString());
                    break;
                default:
                    input += sc.nextLine();
                    Task newPlainTask = new PlainTask(input);
                    list.add(newPlainTask);
                    echoEntry(newPlainTask.toString());

            }
            input = sc.next();
        }
        exit();
    }

    // Helper Functions
    private void greet() {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");
    }

    private void echoEntry(String input) {
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                String.format("       %s \n", input) +
                String.format("     Now you have %d tasks in the list. \n", list.size()) +
                "    ____________________________________________________________");
    }

    private void echoList(List<Task> list) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("     %d.%s",
                    i+1, list.get(i).toString()));
        }
        System.out.println("    ____________________________________________________________");
    }

    private void completeTask(int entryNumber) {
        list.get(entryNumber - 1).setDone();
        System.out.println("    ____________________________________________________________");
        System.out.println("      Nice! I've marked this task as done: ");
        System.out.println(String.format("      %s",
                list.get(entryNumber - 1).toString()));
        System.out.println("    ____________________________________________________________");
    }

    private void exit() {
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
