import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] textArr = new String[100];
        int count = 0;

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String input = sc.nextLine();

        while (!input.equals(null)) {
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < count; i++) {
                    int index = i + 1;
                    System.out.println(index + ". " + textArr[i]);
                }
            } else {
                textArr[count] = input;
                count++;
            }
            input = sc.nextLine();
        }
    }
}