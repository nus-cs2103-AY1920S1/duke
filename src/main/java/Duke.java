import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Duke {
    final static String greet = "Hello! I'm Duke\nWhat can I do for you?";
    final static String goodbye = "Bye. Hope to see you again!";

    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(greet);

        String command = input.readLine();

        while(!command.equals("bye")){
            System.out.println(command);
            command = input.readLine();
        }
        System.out.println(goodbye);
    }
}
