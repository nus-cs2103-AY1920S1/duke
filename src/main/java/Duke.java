import java.util.Scanner;

public class Duke {
    private static Storage storage = new Storage("./data/duke.txt");
    private static Ui ui = new Ui();
    private static TaskList taskList = new TaskList(storage, ui);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] inputArr = input.split("\\s+", 2);
            String command = inputArr[0];
            try {
                switch (command) {
                case "bye":
                    ui.print("Bye! Hope to see you again soon!");
                    return;
                case "list":
                    taskList.printList();
                    break;
                case "done":
                    if (inputArr.length <= 1) {
                        throw new DukeException("☹ OOPS! Task number missing!");
                    }
                    try {
                        taskList.doTask(Integer.parseInt(inputArr[1]));
                    } catch (NumberFormatException e) {
                        throw new DukeException("☹ OOPS! Task number format invalid!");
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS! Task number invalid!");
                    }
                    break;
                case "delete":
                    if (inputArr.length <= 1) {
                        throw new DukeException("☹ OOPS! Task number missing!");
                    }
                    try {
                        taskList.deleteTask(Integer.parseInt(inputArr[1]));
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
                    taskList.addNewTask(new ToDo(inputArr[1]));
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
                    taskList.addNewTask(new Deadline(deadlineInputArr[0], deadlineInputArr[1]));
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
                    taskList.addNewTask(new Event(eventInputArr[0], eventInputArr[1]));
                    break;
                default:
                    throw new DukeException("☹ OOPS! I can't do it!");
                }
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }

        sc.close();
    }
}
