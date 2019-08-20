import java.util.Scanner;
//Test comment
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
    public void introduction() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke();
        duke.introduction();
        while (sc.hasNext()) {
            String command = sc.next();
            if(command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else {
                System.out.println(command);
            }
        }
    }
}