import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "    ____________________________________________________________\n";
        System.out.println(line +
                "     Hello! I'm Duke\n" + //duke greeting
                "     What can I do for you?\n" + line);
        while(sc.hasNext()) {
            String command = sc.nextLine();
            Response.enlist(command); //add to list
            System.out.println(new Response(command));
            if (command.equals("bye")) break; // to exit code
        }
    }
}