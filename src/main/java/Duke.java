import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        String greeting = "Hello! I'm Duke\n" +
                          "What can I do for you?";
        System.out.println(greeting);

        boolean ok = true;
        while(ok){
            Scanner input = new Scanner(System.in);
            String command = input.nextLine();
            switch(command) {
                case "list":
                    System.out.println("list");
                    break;
                case "blah":
                    System.out.println("blah");
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    ok = false;
                    break;
                default:
                    // code block
            }
        }
    }
}
