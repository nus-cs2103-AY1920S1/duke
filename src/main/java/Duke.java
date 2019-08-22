import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        printLogo();
        printWelcome();

        while (true) {
            if (sc.hasNextLine()) {
                String command = sc.nextLine();
                switch (command.split(" ")[0]) {
                    case "bye":
                        printBye();
                        return;
                    case "list":
                        printList(taskList);
                        break;
                    case "done":
                        try {
                            if(command.split(" ").length > 1) {
                                try {
                                    int itemIndex = Integer.parseInt(command.split(" ")[1]);
                                    if(itemIndex > taskList.size() || itemIndex < 1) {
                                        throw new DukeException("The task to be done is not within the list.");
                                    } else {
                                        Task currTask = taskList.get(itemIndex - 1);
                                        currTask.doTask();
                                        printDone(currTask);
                                    }
                                } catch(NumberFormatException e) {
                                    throw new DukeException("The task specified is not a number.");
                                }
                            } else {
                                throw new DukeException("The task to be done is not specified.");
                            }
                        } catch(DukeException e) {
                            printMessage(e.toString());
                        }
                        break;
                    case "todo":
                        try {
                            if(command.split(" ", 2).length > 1) {
                                String todoInput = command.split(" ", 2)[1];
                                Todo todo = new Todo(todoInput);
                                taskList.add(todo);
                                printAddedTask(taskList);
                            } else {
                                throw new DukeException("The description of a todo cannot be empty.");
                            }
                        } catch(DukeException e) {
                            printMessage(e.toString());
                        }
                        break;
                    case "deadline":
                        try {
                            if(command.split(" ", 2).length > 1) {
                                Deadline deadline = makeDeadline(command.split(" ", 2)[1]);
                                taskList.add(deadline);
                                printAddedTask(taskList);
                            } else {
                                throw new DukeException("The description of a deadline cannot be empty");
                            }
                        } catch(DukeException e) {
                            printMessage(e.toString());
                        }
                        break;
                    case "event":
                        try {
                            if(command.split(" ", 2).length > 1) {
                                Event event = makeEvent(command.split(" ", 2)[1]);
                                taskList.add(event);
                                printAddedTask(taskList);
                            } else {
                                throw new DukeException("The description of a todo cannot be empty.");
                            }
                        } catch(DukeException e) {
                            printMessage(e.toString());
                        }
                        break;
                    case "delete":
                        try {
                            if(command.split(" ").length > 1) {
                                try {
                                    int deleteIndex = Integer.parseInt(command.split(" ")[1]);
                                    if(deleteIndex > taskList.size() || deleteIndex < 1) {
                                        throw new DukeException("The task to be deleted is not within the list.");
                                    } else {
                                        Task deleted = taskList.remove(deleteIndex - 1);
                                        printDeletedTask(taskList, deleted);
                                    }
                                } catch(NumberFormatException e) {
                                    throw new DukeException("The task specified is not a number.");
                                }
                            } else {
                                throw new DukeException("The task to be deleted is not specified.");
                            }
                        } catch(DukeException e) {
                            printMessage(e.toString());
                        }
                        break;
                    default:
                        try {
                            throw new DukeException("I'm sorry, but I don't know what that means :-(");
                        } catch(DukeException e) {
                            printMessage(e.toString());
                        }
                        break;
                }
            }
        }
    }

    private static void printIndent() {
        System.out.print("\t");
    }

    private static void printIndentWSpace() {
        System.out.print("\t ");
    }

    private static void printTopLine() {
        String line = "____________________________________________________________";
        printIndent();
        System.out.println(line);
    }

    private static void printBotLine() {
        String line = "____________________________________________________________";
        printIndent();
        System.out.println(line);
    }

    private static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    private static void printWelcome() {
        printTopLine();
        printIndentWSpace();
        System.out.println("Hello! I'm Duke");
        printIndentWSpace();
        System.out.println("What can I do for you?");
        printBotLine();
    }

    private static void printBye() {
        printTopLine();
        printIndentWSpace();
        System.out.println("Bye. Hope to see you again soon!");
        printBotLine();
    }

    private static void printMessage(String msg) {
        printTopLine();
        printIndentWSpace();
        System.out.println(msg);
        printBotLine();
    }

    private static void printAddition(Task task) {
        printMessage(("added: " + task.description));
    }

    private static void printList(ArrayList<Task> list) {
        int count = 1;
        printTopLine();
        printIndentWSpace();
        System.out.println("Here are the task(s) in your list:");
        for(Task item: list) {
            printIndentWSpace();
            System.out.println(count + "." + item);
            count++;
        }
        printBotLine();
    }

    private static void printDone(Task task) {
        printTopLine();
        printIndentWSpace();
        System.out.println("Nice! I've marked this task as done: ");
        printIndentWSpace();
        System.out.println("   " + task);
        printBotLine();
    }

    private static Deadline makeDeadline(String item) throws DukeException {
        String[] words = item.split(" /by ");
        if(words.length > 2) {
            throw new DukeException("There are too many /by in the description.");
        } else if(words.length < 2) {
            throw new DukeException("The description of the deadline is insufficient.");
        } else {
            return new Deadline(words[0], words[1]);
        }
    }

    private static Event makeEvent(String item) throws DukeException {
        String[] words = item.split(" /at ");
        if(words.length > 2) {
            throw new DukeException("There are too many /at in the description");
        } else if(words.length < 2) {
            throw new DukeException("The description of the deadline is insufficient.");
        } else {
            return new Event(words[0], words[1]);
        }
    }

    private static void printAddedTask(ArrayList<Task> list) {
        Task last = list.get(list.size() - 1);
        printTopLine();
        printIndentWSpace();
        System.out.println("Got it. I've added this task:");
        printIndentWSpace();
        System.out.printf("   %s\n", last);
        printIndentWSpace();
        System.out.printf("Now you have %d task(s) in the list.\n", list.size());
        printBotLine();
    }

    private static void printDeletedTask(ArrayList<Task> list, Task task) {
        printTopLine();
        printIndentWSpace();
        System.out.println("Noted. I've removed this task:");
        printIndentWSpace();
        System.out.printf("   %s\n", task);
        printIndentWSpace();
        System.out.printf("Now you have %d task(s) in the list.\n", list.size());
        printBotLine();
    }
}
