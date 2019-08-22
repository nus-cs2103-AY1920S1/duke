import java.util.Scanner;

public class Duke {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm\n" + logo + "What can I do for you?");

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] inputArr = input.split("\\s+", 2);
            String command = inputArr[0];
            try {
                switch (command) {
                    case "bye":
                        System.out.println("Bye! Hope to see you again soon!");
                        return;
                    case "list":
                        Task.printList();
                        break;
                    case "done":
                        if (inputArr.length <= 1) {
                            throw new DukeException("☹ OOPS! Task number missing!");
                        }
                        try {
                            Task.doTask(Integer.parseInt(inputArr[1]));
                        } catch (NumberFormatException e) {
                            throw new DukeException("☹ OOPS! Task number format invalid!");
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("☹ OOPS! Task number invalid!");
                        }
                        break;
                    case "todo":
                        if (inputArr.length <= 1) {
                            throw new DukeException("☹ OOPS! Todo description missing!");
                        }
                        new ToDo(inputArr[1]);
                        break;
                    case "deadline":
                        if (inputArr.length <= 1) {
                            throw new DukeException("☹ OOPS! Deadline description missing!");
                        }
                        String[] deadlineInputArr = inputArr[1].split(" /by ");
                        if (deadlineInputArr.length <= 1) {
                            if (inputArr[1].indexOf("/by") == 0) {
                                throw new DukeException("☹ OOPS! Deadline description format invalid!");
                            } else {
                                throw new DukeException("☹ OOPS! Deadline due date missing!");
                            }
                        }
                        new Deadline(deadlineInputArr[0], deadlineInputArr[1]);
                        break;
                    case "event":
                        if (inputArr.length <= 1) {
                            throw new DukeException("☹ OOPS! Event description missing!");
                        }
                        String[] eventInputArr = inputArr[1].split(" /at ");
                        if (eventInputArr.length <= 1) {
                            if (inputArr[1].indexOf("/at") == 0) {
                                throw new DukeException("☹ OOPS! Event description format invalid!");
                            } else {
                                throw new DukeException("☹ OOPS! Event timing missing!");
                            }
                        }
                        new Event(eventInputArr[0], eventInputArr[1]);
                        break;
                    default:
                        throw new DukeException("☹ OOPS! I can't do it!");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        sc.close();
    }
}
