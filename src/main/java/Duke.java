import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> actions = new ArrayList<String>(100);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        while(input.hasNext()) {
            String command = input.nextLine();
            if(command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list") && actions.size() == 0) {
                System.out.println("No text found.");
            } else if (command.equals("list")) {
                for(int i = 0; i < actions.size(); i++) {
                    System.out.println((i + 1) + ". " + actions.get(i));
                }
            } else {
                actions.add(command);
                System.out.println("added: " + command);
            }
        }
    }
}
