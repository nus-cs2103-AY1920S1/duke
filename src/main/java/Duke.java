import java.util.Scanner;
public class Duke {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String hr = "___________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(hr);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(hr);
        while(true) {
            String command = sc.next();
            System.out.println(hr);
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.print(hr);
                break;
            } else {
                System.out.println(command);
            }
            System.out.println(hr);
        }
    }
}
