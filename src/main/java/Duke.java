import java.util.Scanner;

public class Duke {
    /*
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
    */
    public void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void run() {
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String cmd = sc.next();
            switch(cmd) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    flag = false;
                    break;
                default:
                    System.out.println(cmd);
                    break;
            }
            if (!flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.run();
    }
}
