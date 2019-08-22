import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> repeatList = new ArrayList();

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        boolean byeHit = false;
        String repeatStr = "";

        while (!byeHit) {
            repeatStr = sc.nextLine();
            if (repeatStr.equals("bye")) {
                byeHit = true;
                System.out.println("Bye. Hope to see you again soon!");
            } else if (repeatStr.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= repeatList.size(); i++) {
                    System.out.println(i + ". " + repeatList.get(i - 1));
                }
            } else if (repeatStr.contains("done")) {
                int target = parseInt(repeatStr.replaceAll("\\D+","")) - 1;
                repeatList.get(target).done();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("  " + repeatList.get(target));
            } else {
                repeatList.add(new Task(repeatStr));
                System.out.println("added: " + repeatStr);
            }
        }
    }
}
