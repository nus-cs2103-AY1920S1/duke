import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input;
        boolean dukeIsOn = true;
        ArrayList<String> toDoList = new ArrayList<>(100);
        while(dukeIsOn){
            input = sc.nextLine();
            switch (input) {
                case "list":
                    int total = toDoList.size();
                    for (int i = 0; i < total; i++) {
                        System.out.println((i + 1) + ". " + toDoList.get(i));
                    }
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    dukeIsOn = false;
                    break;
                default:
                    toDoList.add(input);
                    System.out.println("added: " + input);
                    break;
            }
        }
    }
}
