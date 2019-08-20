import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String intro = "Hello! I'm Duke\n" +
                "What can I do for you?\n";

        System.out.println(intro);
        String input;
        while(!(input = sc.nextLine()).equals("bye")) {
            System.out.println(wrap(input));
        };
        String endMessage = "Bye. Hope to see you again soon!";
        System.out.println(wrap(endMessage));
    }

    private static String wrap(String str) {
        return "    ____________________________________________________________\n"
                + "    " + str
                + "\n    ____________________________________________________________";
    }
}
