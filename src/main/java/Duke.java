import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        String next = sc.nextLine();
        int count = 1;
        while (!next.equals("bye")) {
            if (next.equals("list")) {
                for (String text : list) {
                    System.out.println(count + ". " + text);
                    count++;
                }
            } else {
                list.add(next);
                System.out.println("added: " + next);
            }
            next = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

}