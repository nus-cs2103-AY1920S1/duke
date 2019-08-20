import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        String greeting = "Hello! I'm Duke\nWhat can I do for you?";
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(greeting);

        // Start reading input
        while(sc.hasNextLine()){
            String readInput = sc.nextLine();

            // Include case-insensitive bye
            if(readInput.toLowerCase().equals("bye")) {
                System.out.println(goodbye);
                break;
            }

            String processedInput = Duke.process(readInput);
            System.out.println(processedInput);
        }
    }

    // Add in Indentation and horizontal lines
    private static String process(String input){
        String horizontalLines = "\t____________________________________________________________\n";
        return horizontalLines + "\t" + input + "\n" + horizontalLines;
    }
}
