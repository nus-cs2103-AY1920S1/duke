import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printReply("Hello! I'm Duke\n\tWhat can I do for you?");
        String command = sc.next();
        while(!command.equals("bye")){
            printReply(command);
            command = sc.next();
        }
        printReply("Bye. Hope to see you again soon!");
    }
    public static void printReply(String reply){
        System.out.println("\t________________________________________\n\t" + reply
                           + "\n\t________________________________________");
    }
}
