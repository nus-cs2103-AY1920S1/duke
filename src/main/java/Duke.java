import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Task[] itemsLst = new Task[100];
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
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 0; i < currentIndex; i++) {
                        System.out.printf("    %d.[%s] %s\n",
                            i + 1, itemsLst[i].getStatusIcon(), itemsLst[i].description);
                    }
                } else { // if command is neither list nor bye
                    Scanner tempSc = new Scanner(command);
                    // if command is done
                    if (tempSc.hasNext() && tempSc.next().equals("done")) {
                        int tempInt = tempSc.nextInt() - 1;
                        itemsLst[tempInt].markAsDone();
                        System.out.printf("    Nice! I've marked this task as done:\n      [%s] %s\n"
                            , itemsLst[tempInt].getStatusIcon(), itemsLst[tempInt].description);
                    } else {
                        itemsLst[currentIndex] = new Task(command);
                        System.out.printf("    added:[%s] %s\n", itemsLst[currentIndex].getStatusIcon(), command);
                        currentIndex++;
                    }
                }
                System.out.printf("%s\n\n", hLine);
            }

        }

    }
}
