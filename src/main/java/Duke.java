import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    final static String greet = "Hello! I'm Duke\nWhat can I do for you?";
    final static String goodbye = "Bye. Hope to see you again!";

    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> listOfTasks = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(greet);

        String command = input.readLine();

        while(!command.equals("bye")){
            switch (command)
            {
                case "list":
                    //"list" logic
                    int size = listOfTasks.size();
                    for(int i = 0; i < size; i++){
                        String curr = listOfTasks.get(i);
                        System.out.println(i+1 + "." + curr);
                    }
                    break;
                default:
                    System.out.println("added: " + command);
                    listOfTasks.add(command);
                    break;
            }
            command = input.readLine();
        }
        System.out.println(goodbye);
    }
}
