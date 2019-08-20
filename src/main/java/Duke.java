import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/

        Task_List schedule = new Task_List();
        Scanner sc = new Scanner(System.in);
        System.out.println(new Border());
        System.out.println("    Hello! I'm Duke \n    What can I do for you?");
        System.out.println(new Border() + "\n");
        String input = sc.nextLine();
        while (!input.equals("bye")){
            schedule.add(input);
            input = sc.nextLine();
        }
        System.out.println((new Border()) + "\n     Bye. Hope to see you again soon! \n" + (new Border()));

    }
}
