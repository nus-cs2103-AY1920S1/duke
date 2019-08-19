import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        String s;
        while (!(s = sc.nextLine()).equals("bye")) {
            System.out.println(s);
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
