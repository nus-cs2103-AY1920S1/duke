import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            System.out.println(str);
            str = sc.nextLine();
        }
    }
}
