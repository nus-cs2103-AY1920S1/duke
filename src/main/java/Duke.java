import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello I'm Duke! \nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);

        String command = sc.nextLine();
        while (! command.toLowerCase().equals("bye")) {
            System.out.println(command);
            command = sc.nextLine();
        }
        /*
        ArrayList<String> arr = new ArrayList<>();

        System.out.println("Hello I'm Duke! \nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);

        String command = sc.nextLine();
        while (! command.toLowerCase().equals("bye")) {

            if (command.equals("list")) {
                int index  = 1;
                for (String item : arr) {
                    System.out.println(String.format("%d. %s", index ,item));
                    index++;
                }
            } else {
                arr.add(command);
                System.out.println(String.format("added: %s", command));
            }

            command = sc.nextLine();
        }
        */
        System.out.println("Bye. Hope to see you again!");

   }
}
