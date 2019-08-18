import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] itemsLst = new String[100];
        int currentIndex = 0;

        String hLine = "    ____________________________________________________________";

        System.out.println(hLine);
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        System.out.printf("%s\n\n", hLine);

        while(true) {

            String command = scanner.nextLine();

            if (command.equals("bye")) {
                System.out.println(hLine);
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.printf("%s\n\n", hLine);
                break;
            } else {
                System.out.println(hLine);
                if (command.equals("list")) {
                    for (int i = 0; i < currentIndex; i++) {
                        System.out.printf("    %d. %s\n", i + 1, itemsLst[i]);
                    }
                } else { // if command is neither list nor bye
                    itemsLst[currentIndex] = command;
                    currentIndex++;
                    System.out.printf("    added: %s\n", command);
                }
                System.out.printf("%s\n\n", hLine);
            }

        }

    }
}
