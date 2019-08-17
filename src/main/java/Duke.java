import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello I'm Duke\n" + "What can I do for you?");

        String textInput = sc.nextLine();
        while (!textInput.equals("bye")) {
            System.out.println(textInput);
            textInput = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
