import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        TaskList list = new TaskList();
        String command = "";

        while (sc.hasNextLine()) {
            try {
                command = sc.next();
                String errorMsg = String.format("The description of a %s cannot be empty.", command);

                switch (command) {
                    case "list":
                        list.printTasks();
                        break;

                    case "done":
                        int sizeOfList = list.getNumOfTasks();
                        String number = sc.nextLine().trim();

                        if (number.matches("^\\d+")) {
                            int taskNum = Integer.parseInt(number);
                            if (taskNum > sizeOfList || taskNum < 1) {
                                throw new InvalidDescriptionException(errorMsg);
                            } else {
                                list.tickTask(taskNum);
                            }
                        } else {
                            throw new InvalidDescriptionException(errorMsg);
                        }
                        break;

                    case "delete":
                        int taskNo = Integer.parseInt(sc.nextLine().trim());
                        int listSize = list.getNumOfTasks();
                        if (taskNo > listSize || taskNo < 1) {
                            throw new InvalidDescriptionException(errorMsg);
                        } else {
                            list.removeTask(taskNo);
                        }
                        break;

                    case "todo":
                        String input1 = sc.nextLine();
                        if (input1.equals("")) {
                            throw new InvalidDescriptionException(errorMsg);
                        } else {
                            list.addTask(new ToDos(input1));
                        }
                        break;

                    case "deadline":
                        String[] input2 = sc.nextLine().split("/by");
                        if (input2.length != 2) {
                            throw new InvalidDescriptionException(errorMsg);
                        } else {
                            list.addTask(new Deadlines(input2[0], input2[1]));
                        }
                        break;

                    case "event":
                        String[] input3 = sc.nextLine().split("/at");
                        if (input3.length != 2) {
                            throw new InvalidDescriptionException(errorMsg);
                        } else {
                            list.addTask(new Event(input3[0], input3[1]));
                        }
                        break;

                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        return;

                    default:
                        throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            if (sc.hasNext()) {
                continue;
            } else {
                break;
            }
        }
    }
}
