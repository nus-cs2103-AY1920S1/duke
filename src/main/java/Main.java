import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //start Duke program
        Scanner sc = new Scanner(System.in);
        String horizontal_line = "\t__________________________________________________";
        System.out.println(horizontal_line);
        System.out.println("\t  Hello! I'm Duke");
        System.out.println("\t  What can I do for you?");
        System.out.println(horizontal_line);

        //take in inputs
        while (sc.hasNext()) {
            String inputStr = sc.next();

            if (inputStr.equals("bye")) {
                System.out.println(horizontal_line);
                System.out.println("\t  Bye. Hope to see you again soon!");
                System.out.println(horizontal_line);
                break;
            } else {
                System.out.println(horizontal_line);
                System.out.println("\t  " + inputStr);
                System.out.println(horizontal_line);
            }
        }
    }
}
