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

                list[index].isDone = true;

                System.out.println(list[index]);

            } else if (command.equals("list")) {

                System.out.println("Here are the tasks in your list:");

               for (int i = 0; i < listIndex; i++) {
                   System.out.println(i+1 + "." + list[i]);
               }

           } else if (command.equals("") == false) {
                list[listIndex++] = new Task(command);
           }

            command = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
