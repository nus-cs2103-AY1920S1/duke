import java.awt.*;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    Hello from\n" + logo + "    Hello! I'm Duke\n    What can I do for you?");
        String input  = sc.nextLine();
        String[] wordArray = input.split(" ");
        ListOfInput list = new ListOfInput();
        while (!wordArray[0].equals("bye")) {
            try {
                switch (wordArray[0]) {
                    case "list":
                        list.printList();
                        break;
                    case "done":
                        int num = Integer.parseInt(wordArray[1]);
                        list.markAsDone(num);
                        break;
                    case "todo":
                    case "event":
                    case "deadline":
                        list.addToList(input);
                        break;
                    default:
                        throw new DukeException();
                }
            } catch (DukeException e) {
                System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            input = sc.nextLine();
            wordArray = input.split(" ");
        }
        System.out.println("    Bye. Hope to see you again soon!");
    }
}
