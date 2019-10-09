import java.util.Scanner;

public class Echo {
    public static void RecurrEcho() {
        Scanner sc = new Scanner(System.in);
        String bye = "____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";

        String echo = sc.nextLine();
        if (echo.equals("bye")) {
            System.out.println(bye);
        } else {
            System.out.println("____________________________________________________________\n" +
                    echo +"\n" +
                    "____________________________________________________________\n");
            RecurrEcho();
        }

    }
    public static void main(String[] args) {
        String greet = "___________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(greet);
        RecurrEcho();
    }

}
