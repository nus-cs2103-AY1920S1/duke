import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();

        String[] list = new String[100];
        int listIndex = 0;

        while (command.equals("bye") == false) {

            String[] words = command.split(" ",2);

            if (words[0].equals("added:")) {
                String addMe = words.length < 2 ? "" : words[1];
                list[listIndex++] = addMe;

            } else if (command.equals("list")) {

               for (int i = 0; i < listIndex; i++) {
                   System.out.print(i+1 + ". ");
                   System.out.println(list[i]);
               }

           } else {
               System.out.println(command);
           }

            command = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
