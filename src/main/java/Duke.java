import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    public static void main(String[] args) {

        String greet = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";

        System.out.println(greet);

        while(true) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String input = br.readLine();

                switch(input) {
                    case "bye":
                        System.out.println("____________________________________________________________\n"
                                + "Bye. Hope to see you again soon!\n"
                                + "____________________________________________________________\n");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("____________________________________________________________\n"
                                + input + "\n"
                                + "____________________________________________________________\n");

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
