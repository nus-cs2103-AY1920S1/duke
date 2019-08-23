import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input;
        String[] splitInput;
        boolean dukeIsOn = true;
        ToDoList myTasks = new ToDoList();
        while(dukeIsOn){
            input = sc.nextLine().trim();
            splitInput = input.split(" ");
            try {
                switch (splitInput[0]) {
                    case "list":
                        myTasks.listAllTasks();
                        break;
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        dukeIsOn = false;
                        break;
                    case "done":
                        myTasks.checkTask(Integer.parseInt(splitInput[1]) - 1);
                        break;
                    case "todo":
                        if (input.length() <= 4) {
                            throw (new EmptyTodoDscDukeException("todo task has empty description."));
                        }
                        myTasks.addTask(input.substring(5), TaskType.TODO);
                        break;
                    case "deadline":
                        if (input.length() <= 8) {
                            throw (new EmptyDeadlineDscDukeException("deadline task has empty description."));
                        }
                        myTasks.addTask(input.substring(9), TaskType.DEADLINE);
                        break;
                    case "event":
                        if (input.length() <= 5) {
                            throw (new EmptyEventDscDukeException("event task has empty description."));
                        }
                        myTasks.addTask(input.substring(6), TaskType.EVENT);
                        break;
                    case "delete":
                        if (input.length() <= 6) {
                            throw new InvalidTaskIndexDukeException("No task number was given.");
                        }
                        myTasks.deleteTask(Integer.parseInt(splitInput[1]) - 1);
                        break;
                    default:
                        throw(new UnknownCmdDukeException(splitInput[0] + " is not a known command."));

                }
            } catch (UnknownCmdDukeException e){
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (EmptyTodoDscDukeException e) {
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            } catch (EmptyDeadlineDscDukeException e) {
                System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            } catch (EmptyEventDscDukeException e) {
                System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
            } catch (NoDateDukeException e) {
                System.out.println("☹ OOPS!!! You need to provide a date, with / to indicate it:-(");
            } catch (InvalidTaskIndexDukeException e) {
                System.out.println("☹ OOPS!!! You need to provide a valid task number :-(");
            } catch (NumberFormatException e) {
                System.out.println("☹ OOPS!!! You need to provide a valid number :-(");
            }
        }
    }
}
