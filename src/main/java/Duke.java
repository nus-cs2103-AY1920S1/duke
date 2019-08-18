import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);

        while(command = sc.nextLine() != "bye") {
            System.out.println(command);
        }

        System.out.println("bye!");
        System.exit(0);
    }
}
