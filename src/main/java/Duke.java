import java.util.Scanner;

public class Duke {
    static String[] arr = new String[100];
    static int count = 0;
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke\n" +
                "     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String word = sc.nextLine();
            System.out.println("    ____________________________________________________________");
            if (word.equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                break;
            } else if (word.equals("list")) {
                System.out.println("    ____________________________________________________________");
                for (int i = 1; i < count + 1; i++) {
                        System.out.print("     " + i + ". ");
                        System.out.println(arr[i-1]);
                }
            } else {
                arr[count] = word;
                count++;
                System.out.println("     added: " + word);
            }
            System.out.println("    ____________________________________________________________");
        }
    }
}
