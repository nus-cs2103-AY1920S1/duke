import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String inputText = sc.nextLine();

        while (!inputText.equals("bye")) {
            System.out.println(inputText);
            inputText = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}


