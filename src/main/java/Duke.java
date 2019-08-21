import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        int book = 1;
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------------");
        System.out.println();
        while(book == 1) {
            String in = sc.nextLine();
            System.out.println("--------------------------------------");
            System.out.print("");
            if(in.contentEquals("bye")) {
                System.out.print("Bye. ");
                System.out.println("Hope to see you again soon!");
                System.out.println();
                book = 0;
            } else {
                System.out.println(in);
                System.out.println();
            }
            System.out.println("--------------------------------------");
        }
    }
}
