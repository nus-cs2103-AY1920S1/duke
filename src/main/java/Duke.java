import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Duke {
    public static void main(String[] args) {
        String logo =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        Duke d = new Duke();
        d.greeting();
        d.listen();
    }

    private Duke() {}

    private void greeting() {
        System.out.println("Hello! I'm Duke");
    }

    private ArrayList<Task> cache = new ArrayList<>();
    private void listen() {
        Scanner listener = new Scanner(System.in);
        while(listener.hasNext()) {
            String line = listener.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            } else if (line.equals("list")) {
                showList();
            } else {
                handleTask(line);                
            }
        }
    }

    private void handleTask(String line) {
        // something else: help message
        // done <number>
        // todo <desc>
        // deadline <desc> /by <time>
        // event desc /at <time>
        
        Scanner wordReader = new Scanner(line);
        String command = wordReader.next();
        try{
            switch (command) {
                case "done":
                    handleDone(wordReader.nextLine());
                    break;
                case "todo":
                    handleToDo(wordReader.nextLine());
                    break;
                case "deadline":
                    handleDeadline(wordReader.nextLine());
                    break;
                case "event":
                    handleEvent(wordReader.nextLine());
                    break;
                default:
                    printHelpMessage();
                    break;
            }
        } catch (NoSuchElementException e) {
            System.out.println("A command must be followed by one or more arguments! Type 'help' for more help");
            banner();
        }
    }

    private void printHelpMessage() {
        System.out.println("Available commands:\n" +
                "event <desc> /at <time>\n" +
                "todo <desc>\n" +
                "deadline <desc> /by <time>\n" +
                "list\n" +
                "done <number>\n");
    }
    
    private void handleDone(String number) {
        // parse the number, mark as done
        try {
            int n = Integer.parseInt(number.trim());
            Task t = cache.get(n-1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(t);
            banner();
        } catch (NullPointerException e) {
            System.out.println("Hey bro why no number?");
            banner();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("The format is: 'done <number>'. Please try again.");
            banner();
        }
    }

    private void handleEvent(String descAt) {
        // separate by /at
        String[] splitted = descAt.split("/at");
        if (splitted.length < 2) {
            System.out.println("Please enter a non-empty description and time, in the following format:");
            System.out.println("event <desc> /at <time>");
            banner();
            return;
        }

        String desc = splitted[0];
        List<String> restAsList = Arrays.asList(
                Arrays.copyOfRange(splitted, 1, splitted.length));
        String at = String.join("/at", restAsList);

        Event e = new Event(desc, at);
        cache.add(e);
        printAddConfirmation();
    }

    private void handleDeadline(String descBy) {
        // separate by /by
        String[] splitted = descBy.split("/by");
        if (splitted.length < 2) {
            System.out.println("Please enter a non-empty description and time, in the following format:");
            System.out.println("deadline <desc> /by <time>");
            banner();
            return;
        }

        String desc = splitted[0];
        List<String> restAsList = Arrays.asList(
                Arrays.copyOfRange(splitted, 1, splitted.length));
        String by = String.join("/by", restAsList);

        Deadline e = new Deadline(desc, by);
        cache.add(e);
        printAddConfirmation();
    }
    
    private void handleToDo(String desc) {
        cache.add(new ToDo(desc));
        printAddConfirmation();
    }

    private void printAddConfirmation() {
        System.out.println("Got It. I've added this task:");
        System.out.println(cache.get(cache.size()-1)); // last object
        System.out.printf("Now you have %d tasks in the list.\n", cache.size());
        banner();
    }
    
    private void showList() {
        for (int i=0; i<cache.size(); i++) {
            Task t = cache.get(i);
            System.out.printf("%d.%s\n", i+1, t);
        }
        banner();
    }

    private void banner() {
        System.out.println();
    }
}
