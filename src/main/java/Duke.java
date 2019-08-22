import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        String in;
        while (true){
            in = sc.nextLine().trim();
            if (in.equals("bye")) {
                System.out.println("Bye!");
                break;
            } else {
                System.out.println(in); 
            }
        }
    }
}
