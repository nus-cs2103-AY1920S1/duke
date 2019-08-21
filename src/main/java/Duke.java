import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Duke d = new Duke();

        d.initDuke();
        d.terminateDuke();

    }

    public void initDuke() {
        System.out.println("\t____________________________________________________________\n"
                          +"\tHello! I'm Duke\n"
                          +"\tWhat can I do for you?\n"
                          +"\t____________________________________________________________\n");

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
    }

    public void terminateDuke() {
        System.out.println("\t____________________________________________________________\n"
                          +"\tBye. Hope to see you again soon!\n"
                          +"\t____________________________________________________________\n");
    }
}
