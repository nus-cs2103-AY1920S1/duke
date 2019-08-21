import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //duke greeting
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");

        while(sc.hasNext()) {
            String command = sc.next();
            System.out.println(new Response(command));
            if (command.equals("bye")) break; // to exit code
        }
    }
}
