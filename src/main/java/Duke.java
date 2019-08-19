import java.util.Scanner;
import java.util.ArrayList;
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

        ArrayList<String> tasks = new ArrayList<String>();
        boolean ok = true;
        while(ok){
            Scanner input = new Scanner(System.in);
            String command = input.nextLine();
            switch(command) {
                case "list":
                    int counter = 0;
                    for(String s : tasks) {
                        counter ++;
                        System.out.println(counter + ". " + s);
                    }
                    break;
                case "blah":
                    System.out.println("blah");
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    ok = false;
                    break;
                default:
                    tasks.add(command);
                    System.out.println("added: " + command);
            }
        }
    }
}
