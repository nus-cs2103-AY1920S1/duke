import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am\n" + logo);
        System.out.println("What can I do for you?");

        //Echo
        Scanner sc = new Scanner(System.in);
        String input;
        String check = "dummy";
        ArrayList<Task> taskStorage = new ArrayList<>();

        while (check.equals("bye") == false) {
            input = sc.nextLine();
            check = input.toLowerCase();
            if (check.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= taskStorage.size(); i++) {
                    Task evaluatingTask = taskStorage.get(i - 1);
                    System.out.println(i + ". [" + evaluatingTask.getStatusIcon() + "] " + evaluatingTask.getDescription());
                }
                System.out.println();
                continue;
            }

            String[] doneMarkers = check.split(" ");
            if(doneMarkers[0].equals("done")) {
                Task taskDone = taskStorage.get(Integer.valueOf(doneMarkers[1]) - 1);
                taskDone.markAsDone();
                System.out.println("Nice! I've marked this task as done: " + "\n"
                        + "  [" + taskDone.getStatusIcon() + "] " + taskDone.getDescription() + "\n");
            } else if (check.equals("bye") == false) {
                Task t = new Task(input);
                taskStorage.add(t);
                System.out.println("added: " + t.getDescription() + "\n");
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
