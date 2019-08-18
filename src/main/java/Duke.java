import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /**
     * Main method.
     * 
     * @param args arguments passed into main
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        int counter = 0;

        System.out.println("Hello I'm Duke\n" + "What can I do for you?");

        String textInput = sc.nextLine();
        while (!textInput.equals("bye")) {
            if (textInput.equals("list")) {
                for (int i = 1; i <= counter; i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
            } else {
                System.out.println("added: " + textInput);
                list.add(textInput);
                counter++;
            }

            textInput = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

        // Close the scanner
        sc.close();
    }
}
