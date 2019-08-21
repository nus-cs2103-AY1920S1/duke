import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        LinkedList<Task> taskList = new LinkedList<>();

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String inputText = sc.nextLine();

        while (!inputText.equals("bye")) {
            String[] keyList = inputText.split(" ", 2);
            String actionKey = keyList[0];

            try {
                if (inputText.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    int counter = 1;

                    for (Task subTask : taskList) {
                        System.out.println(counter + ". " + subTask);
                        counter++;
                    }
                } else if (actionKey.equals("done")) {
                    System.out.println("Nice! I've marked this task as done: ");

                    int index = Integer.parseInt(inputText.split(" ")[1]);
                    Task selectedTask = taskList.get(index - 1);
                    selectedTask.markAsDone();

                    System.out.println("[" + selectedTask.getStatusIcon() + "] " + selectedTask.getDescription());
                } else {
                    if (actionKey.equals("deadline")) {
                        if (keyList.length <= 1) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }

                        String[] contentList = keyList[1].split(" /by ");
                        if (contentList.length <= 1) {
                            throw new DukeException("☹ OOPS!!! Time need to be specified");
                        }

                        System.out.println("Got it. I've added this task: ");
                        Deadline newDeadline = new Deadline(contentList[0], contentList[1]);
                        System.out.println(newDeadline);
                        taskList.add(newDeadline);
                    } else if (actionKey.equals("event")) {
                        if (keyList.length <= 1) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }

                        String[] contentList = keyList[1].split(" /at ");
                        if (contentList.length == 1) {
                            throw new DukeException("☹ OOPS!!! Time need to be specified");
                        }

                        System.out.println("Got it. I've added this task: ");
                        Event newEvent = new Event(contentList[0], contentList[1]);
                        System.out.println(newEvent);
                        taskList.add(newEvent);
                    } else if (actionKey.equals("todo")) {
                        if (keyList.length <= 1) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }

                        System.out.println("Got it. I've added this task: ");
                        Todo newTodo = new Todo(inputText.split(" ", 2)[1]);
                        System.out.println(newTodo);
                        taskList.add(newTodo);
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                }
            } catch (DukeException err) {
                System.out.println(err.getMessage());
            } finally {
                inputText = sc.nextLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}


