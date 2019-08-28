import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("     ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "     ____________________________________________________________\n");

        Scanner sc = new Scanner(System.in);
        ArrayList<String> data = new ArrayList<String>();
        ArrayList<String> done = new ArrayList<String>();

        do{
            String input = sc.nextLine();

            if(input.equals("bye")){
                System.out.println("     ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "     ____________________________________________________________");
                break;
            } else if(input.equals("list")) {
                int i;
                System.out.println("     ____________________________________________________________\n");
                for(i = 0; i < data.size(); i++){
                    System.out.println("     " + (i + 1) + ". [" + done.get(i) + "] " + data.get(i));
                }
                System.out.println("     ____________________________________________________________");

            } else if(input.startsWith("done")) {
                String[] sp = input.split(" ", 2);
                int index = Integer.parseInt(sp[1]);

                done.set(index - 1, "✓");

                System.out.println("     ____________________________________________________________");

                System.out.println("Nice! I've marked this task as done:");
                System.out.println("     [" + done.get(index - 1) + "] " + data.get(index - 1));

                System.out.println("     ____________________________________________________________");



            } else {
                System.out.println("     ____________________________________________________________\n" +
                        "     added: " + input + "\n" +
                        "     ____________________________________________________________");

                data.add(input);
                done.add("✗");
            }

        } while(true);
    }
}
