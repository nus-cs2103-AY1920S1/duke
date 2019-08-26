import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();

        //Task[] list = new Task[100];
        String[] list = new String[100];
        int listIndex = 0;

        while (command.equals("bye") == false) {

            if (command.equals("list") == true) {
                for (int i = 0; i < listIndex; i++) {
                    System.out.print(i+1 + ". ");
                    System.out.println(list[i]);
                }
            } else {
                list[listIndex++] = command;
            }

            /*
            String[] words = command.split(" ",2);

            if (words[0].equals("added:")) {
                String description = words.length < 2 ? "" : words[1];
                list[listIndex++] = new Task(description);

            } else if (words[0].equals("done") && words.length == 2) {
                int index = Integer.valueOf(words[1]);
                list[index].isDone = true;
            } else if (command.equals("list")) {

               for (int i = 0; i < listIndex; i++) {
                   System.out.print(i+1 + list[i].getStatusIcon() + ". ");
                   System.out.println(list[i]);
               }

           } else {
               System.out.println(command);
           }*/

            command = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
