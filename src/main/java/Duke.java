import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        boolean byeHit = false;
        String repeatStr = "";

        while (!byeHit) {
            repeatStr = sc.nextLine();
            if (repeatStr.equals("bye")) {
                byeHit = true;
                System.out.println("Bye. Hope to see you again soon!");
            } else {
                System.out.println(repeatStr);
            }
        }
    }
}
