import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String lines = "    ____________________________________________________________\n";
        String indent = "    ";
        String logo = lines
                    + "     ____        _           \n"
                    + "    |  _ \\ _   _| | _____   \n"
                    + "    | | | | | | | |/ / _ \\  \n"
                    + "    | |_| | |_| |   <  __/   \n"
                    + "    |____/ \\__,_|_|\\_\\___|\n"
                    + "    Hello! I'm Duke          \n"
                    + "    What can I do for you?   \n"
                    + lines;
        System.out.print(logo);
        String comd = sc.next();
        while (!comd.equals("bye")) {
            System.out.println(lines + indent+ comd + "\n" + lines);
            comd = sc.next();
        }
        System.out.println(lines + indent + "Bye. Hope to see you again soon!\n" + lines);
    }
}
