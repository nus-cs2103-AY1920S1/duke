import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean bye = false;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "    ____________________________________________________________";
        System.out.println("Hello from\n" + logo);

        System.out.println(line + "\n" + "     Hello! I'm Duke\n     What can I do for you?\n" + line);

        while (!bye) {
            String command = sc.next();
            if (!command.equals("bye")) {
                System.out.println(line + "\n     " + command + "\n" + line);
            } else {
                bye = true;
                System.out.println(line + "\n" + "     Bye. Hope to see you again soon!\n" + line);
            }
        }
    }
}
