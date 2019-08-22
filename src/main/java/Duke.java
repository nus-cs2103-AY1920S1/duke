import java.util.Scanner;

public class Duke {

    private static String[] list = new String[100];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String command = sc.nextLine();
        int counter = 0;
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                showList(counter);
            } else {
                list[counter] = command;
                System.out.println("added: " + command);
                counter++;
            }
            command = sc.nextLine();
        }
        if (command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            sc.close(); //exit the program
        }
    }

    // To print out the items stored in the list.
    public static void showList(int counter) {
        for (int i = 0; i < counter; i++) {
            System.out.println((i+1) + ". " + list[i]);
        }
    }
}
