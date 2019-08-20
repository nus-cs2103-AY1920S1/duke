import java.util.Date;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String message;
//
////        String logo = " ____        _        \n"
////                + "|  _ \\ _   _| | _____ \n"
////                + "| | | | | | | |/ / _ \\\n"
////                + "| |_| | |_| |   <  __/\n"
////                + "|____/ \\__,_|_|\\_\\___|\n";
////        System.out.println("Hello from " + logo);
//        System.out.println(sc.nextLine());


        String upperLine = "____________________________________________________________\n";
        String greet = "Hello! I'm Duke\n"
        + "What can I do for you?\n";

        String lowerLine = "____________________________________________________________\n";
        greet = upperLine + greet + lowerLine;
        System.out.println(greet);
        while(true){
            String cmd = sc.next();
            if(cmd.equals("bye")){
                message = upperLine + "Bye. Hope to see you again soon!\n" + lowerLine;
                System.out.println(message);
                break;
            }else{
                message = upperLine + cmd + "\n" + lowerLine;
                System.out.println(message);
            }
        }

    }
}