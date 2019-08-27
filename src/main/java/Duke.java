import com.sun.source.tree.WhileLoopTree;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");

        Scanner input = new Scanner(System.in);
        String user = input.nextLine();

        while (!user.equals("bye")) {
            System.out.println("    ____________________________________________________________\n" +
                    "     " + user +"\n" +
                    "    ____________________________________________________________");
            user =input.nextLine();
        }

        System.out.print("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
