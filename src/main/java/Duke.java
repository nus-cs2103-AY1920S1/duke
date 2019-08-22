import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/

        Scanner S = new Scanner(System.in);
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
        String inp = S.next();
        while(!(inp.equals("bye"))) {
            System.out.println("    ____________________________________________________________\n     " + inp +
                    "\n    ____________________________________________________________");
            inp = S.next();
        }
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n");
    }
}
