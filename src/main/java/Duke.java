import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line= "    ____________________________________________________________";
        String indent= "     ";
        Scanner s = new Scanner(System.in);
        System.out.println(line);
        System.out.println(indent + "Hello! I'm Duke");
        System.out.println(indent + "What can I do for you?");
        System.out.println(line);
        String msg = s.nextLine();
        while(!msg.equals("bye")){
            System.out.println(line);
            System.out.println(indent + msg);
            System.out.println(line);
            msg = s.nextLine();
        }
        System.out.println(line);
        System.out.println(indent + "Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
