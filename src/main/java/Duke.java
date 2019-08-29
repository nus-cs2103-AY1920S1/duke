import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String blankLines = "____________________________________________________________";
        System.out.println("    Hello from\n" + logo);
        System.out.println(blankLines);
        System.out.println("    Hello, I'm Duke!\n    What can I do for you?");
        System.out.println(blankLines);

        Scanner input = new Scanner(System.in);
        String command = input.next();
        while(!command.equals("bye")){
            switch(command){
                case "list":
                    System.out.println("    list");
                    command = input.next();
                    break;

                case "blah":
                    System.out.println("    blah");
                    command = input.next();
                    break;

                default:
                    System.out.println("    Invalid Command");
                    command = input.next();
                    break;
            }
        }
        System.out.println("    Bye. Hope to see you again!");
    }
}
