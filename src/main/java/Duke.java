import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //greet user
        String liner = "    ____________________________________________________________";
        String greeting = "     Hello! I'm Duke\n     What can I do for you?";
        System.out.println(liner + "\n" + greeting + "\n" + liner);

        String input;
        Scanner sc = new Scanner(System.in);
        while (!(input = sc.nextLine()).equals("bye")) {
            System.out.println(liner);
            System.out.println("     " + input);
            System.out.println(liner);
        }

        System.out.println(liner + "\n     Bye. Hope to see you again soon!\n" + liner);
    }
}
