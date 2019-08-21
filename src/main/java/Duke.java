import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Integer num;
        Task curr;
        ArrayList list = new ArrayList<Task>();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        while (!line.equals("bye")) {
            String[] words = line.split(" ");
            if (line.equals("list")) {
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
                line = sc.nextLine();
            } else if (words[0].equals("done")) {
                num = Integer.valueOf(words[1]);
                curr = (Task) list.get(num - 1);
                curr.setStatusIcon(true);
                System.out.println("Nice! I've marked this task as done: \n  " + curr);
                line = sc.nextLine();
            } else {
                System.out.println("added: " + line);
                list.add(new Task(line));
                line = sc.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
