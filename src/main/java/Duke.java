import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I'm\n" + logo);
        System.out.println("What can I do for you?");

        Scanner s = new Scanner(System.in);
        String echo = s.nextLine();
        while(true){
            if(echo.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(echo);
            echo = s.nextLine();
        }
    }
}
