import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        LinkedList<String> lst = new LinkedList<>();

        Scanner sc = new Scanner(System.in);
        String word = sc.next();

        while (!word.equals("bye")) {
            if (word.equals("list")) {
                int counter = 1;
                for (String s: lst) {
                    System.out.println(counter + ". " + s);
                    counter++;
                }
            } else {
                System.out.println("added: " + word);
                lst.add(word);
            }
            word = sc.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
