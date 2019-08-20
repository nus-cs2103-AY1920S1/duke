import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        while(true) {
            String next = sc.nextLine();
            if (!next.equals("bye")) {
                System.out.println(next);
            } else {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
        }
    }
}
