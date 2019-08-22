import java.util.InputMismatchException;
import java.util.Scanner;

public class Duke {
    static Task[] arr = new Task[100];
    static int count = 1;

    public static void main(String[] args) {
        Formatter formatter = new Formatter();
        MessageGenerator greeter = new MessageGenerator();
        Storage storage = new Storage();
        Parser parser = new Parser();
        greeter.greet();
        Scanner sc = new Scanner(System.in);
        loop:
        while (sc.hasNext()) {
            String first = sc.next();
            try {
                switch (first) {
                    case "bye":
                        greeter.bye();
                        break loop;
                    case "list":
                        storage.printList();
                        break;
                    case "done":
                            try {
                                if (!sc.hasNextInt()) {
                                    throw new InvalidItemException();
                                }
                                int number = sc.nextInt();
                                storage.setDone(number);
                            } catch (InvalidItemException e) {
                                e.printError();
                            }
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        String line = sc.nextLine();
                        String[] description = line.split(" ");
                        try {
                            if (description.length <= 1) {
                                throw new MissingInputException(first);
                            }
                            storage.addTask(parser.createNewTask(count, first, description));
                            count++;
                        } catch (MissingInputException e) {
                            e.printError();
                        }
                        break;
                    case "delete":
                        try {
                            if (!sc.hasNextInt()) {
                                throw new InvalidItemException();
                            }
                            int number = sc.nextInt();
                            storage.deleteTask(number);
                        } catch (InvalidItemException e) {
                            e.printError();
                        }
                    default:
                        throw new InvalidCommandException();
                }
            } catch (DukeException e) {
                System.out.println("Here again");
                e.printError();
            }
        }

        }
}

