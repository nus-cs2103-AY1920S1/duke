import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelThree {
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
                default:
                    Task newTask = new Task(input);
                    list.add(newTask);
                    echoEntry(input);
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
                String.format("     added: %s\n", input) +
                "    ____________________________________________________________");
    }

    private void echoList(List<Task> list) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("     %d.[%c] %s",
                    i+1, list.get(i).getStatusIcon(), list.get(i).toString()));
        }
        System.out.println("    ____________________________________________________________");
    }

    private void completeTask(int entryNumber) {
        list.get(entryNumber - 1).setDone();
        System.out.println("    ____________________________________________________________");
        System.out.println("      Nice! I've marked this task as done: ");
        System.out.println(String.format("      [%c] %s",
                list.get(entryNumber - 1).getStatusIcon(), list.get(entryNumber - 1).toString()));
        System.out.println("    ____________________________________________________________");
    }

    private void exit() {
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
