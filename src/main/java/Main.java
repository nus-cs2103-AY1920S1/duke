import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //start Duke program
        Scanner sc = new Scanner(System.in);
        String horizontal_line = "\t__________________________________________________";
        System.out.println(horizontal_line);
        System.out.println("\t  Hello! I'm Duke");
        System.out.println("\t  What can I do for you?");
        System.out.println(horizontal_line);

        //store the list
        ArrayList<String> strArr = new ArrayList<>();

        //take in inputs
        while (sc.hasNext()) {
            String inputStr = sc.nextLine();

            if (inputStr.equals("bye")) {
                System.out.println(horizontal_line);
                System.out.println("\t  Bye. Hope to see you again soon!");
                System.out.println(horizontal_line);
                break;
            } else if (inputStr.equals("list")) {
                System.out.println(horizontal_line);
                for (int i = 0; i < strArr.size(); i++) {
                    System.out.println("\t  "+ (i + 1) +". " + strArr.get(i));
                }
                System.out.println(horizontal_line);
            } else {
                strArr.add(inputStr);
                System.out.println(horizontal_line);
                System.out.println("\t  added: " + inputStr);
                System.out.println(horizontal_line);
            }
        }
    }
}
