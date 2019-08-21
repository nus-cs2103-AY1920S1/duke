import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>();

        while (sc.hasNext()) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                break;
            } else if (str.equals("list")) {
                if (list.size() == 0) {
                    System.out.println("Nothing added yet");
                } else {
                    for (int i = 1; i <= list.size(); i++) {
                        System.out.println("" + i + ". " + list.get(i - 1));
                    }
                }
            } else if (str.substring(0, 4).equals("done")) {
                Integer taskNum = Integer.valueOf(str.substring(5));
                Task currTask = list.get(taskNum-1);
                currTask.markAsDone();
                System.out.println("Nice! I've markedthis task as done:" + "\n" + "\u2713 " + currTask.getDescription());
            } else {
                System.out.println("added: " + str);
                list.add(new Task(str));
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
