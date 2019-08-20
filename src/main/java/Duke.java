import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //greet user
        String liner = "    ____________________________________________________________";
        String greeting = "     Hello! I'm Duke\n     What can I do for you?";
        System.out.println(liner + "\n" + greeting + "\n" + liner);

        String input;
        String[] tasks = new String[100];
        int counter = 0;
        Scanner sc = new Scanner(System.in);
        while (!(input = sc.nextLine()).equals("bye")) {
            if (input.equals("list")) {
                System.out.println(liner);
                for (int i = 0; i < counter; i++ ) {
                    int num = i + 1;
                    System.out.println("     " + num + ". " + tasks[i]);
                }
                System.out.println(liner);
            } else {
                tasks[counter] = input;
                System.out.println(liner);
                System.out.println("     added: " + input);
                System.out.println(liner);
                counter++;
            }
        }

        System.out.println(liner + "\n     Bye. Hope to see you again soon!\n" + liner);
    }
}
