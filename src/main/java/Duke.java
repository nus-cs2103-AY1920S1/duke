import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();

        Task[] list = new Task[100];
        int listIndex = 0;

        while (command.equals("bye") == false) {

            String[] words = command.split(" ",2);

            if (words[0].equals("done") && words.length == 2) {

                System.out.println("Nice! I've marked this task as done:");

                int index = Integer.valueOf(words[1]) - 1;

                list[index].markAsDone();

                System.out.println(list[index]);

            } else if (words[0].equals("todo") && words.length == 2) {

                System.out.println("Got it. I've added this task:");

                String description = words[1];

                list[listIndex++] = new Todo(description);

                System.out.printf("Now you have %d tasks in the list.\n", listIndex);

            } else if (words[0].equals("deadline") && words.length == 2) {

                System.out.println("Got it. I've added this task:");

                String[] split = words[1].split(" /by ", 2);

                String description = split[0];

                String by = split[1];

                list[listIndex++] = new Deadline(description, by);

                System.out.printf("Now you have %d tasks in the list.\n", listIndex);

            } else if (words[0].equals("event") && words.length == 2) {

                System.out.println("Got it. I've added this task:");

                String[] split = words[1].split(" /at ", 2);

                String description = split[0];

                String at = split[1];

                list[listIndex++] = new Event(description, at);

                System.out.printf("Now you have %d tasks in the list.\n", listIndex);

            } else if (command.equals("list")) {

                System.out.println("Here are the tasks in your list:");

               for (int i = 0; i < listIndex; i++) {
                   System.out.println(i+1 + "." + list[i]);
               }

           } else if (command.equals("") == false) {
                list[listIndex++] = new Task(command);

                System.out.println("added: " + command);
           }

            command = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
