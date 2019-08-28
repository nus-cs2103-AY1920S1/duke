import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("     ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "     ____________________________________________________________\n");

        Scanner sc = new Scanner(System.in);
        String data[] = new String[100];
        int counter = 0;

        do{
            String input = sc.nextLine();

            if(input.equals("bye")){
                System.out.println("     ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "     ____________________________________________________________");
                break;
            } else if(input.equals("list")) {
                int i;
                System.out.println("     ____________________________________________________________\n");
                for(i = 1; i <= counter; i++){
                    System.out.println("     " + i + ". " + data[i - 1]);
                }
                System.out.println("     ____________________________________________________________");

            } else {
                System.out.println("     ____________________________________________________________\n" +
                        "     added: " + input + "\n" +
                        "     ____________________________________________________________");

                data[counter] = input;
                counter ++;
            }
        } while(true);
    }
}
