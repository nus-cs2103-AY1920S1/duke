import java.util.*;

public class Duke {
    private static ArrayList<String> list =  new ArrayList<String>();
    public static void showList() {
        int counter = 0;
        for (String item : list) {
            System.out.println(++counter + ". " + item);
        }
    }

    public static void main(String[] args) {
        boolean active = true;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String intro = "Hello! I'm Duke What can I do for you?";
        System.out.println(intro);

        try(Scanner scanner = new Scanner(System.in)) {
            
            while (active && scanner.hasNextLine()) {
                String input = scanner.nextLine();

                switch (input) {
                    case "bye":
                    active = false;
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                    case "list":
                    showList();
                    break;
                    default:
                    list.add(input);
                    System.out.println("added: " + input);
                }
            }
        };
        
    }
}
