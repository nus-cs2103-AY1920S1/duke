import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        String separationLine = "    ____________________________________________________________";
        System.out.println(separationLine + "\n" + logo + "\n     Hello! I'm Duke\n     What can I do for you?\n"
                + separationLine + "\n");

        ArrayList<String> inputStore = new ArrayList<>(100);
        String userInput = scan.nextLine();
        while(!"bye".equals(userInput)) {
            if ("list".equals(userInput)) {
                System.out.println(separationLine);
                for (String s : inputStore) {
                    System.out.println("     " + (inputStore.indexOf(s) + 1) + ". " + s);
                }
                System.out.println(separationLine);
            } else {
                inputStore.add(userInput);
                System.out.println(separationLine + "\n     added: " + userInput + "\n" + separationLine + "\n");
            }
            userInput = scan.nextLine();
        }
        System.out.println(separationLine + "\n     Bye. Hope to see you again soon!\n" + separationLine);
    }
}
