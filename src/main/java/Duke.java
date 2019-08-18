import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    static List<Task> listOfInputs = new ArrayList<>(100);

    public static void todoCheck(String[] tasks) throws DukeException {
        if(tasks.length <= 1) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }
    public static void deadlineCheck(String[] tasks, String userInput) throws DukeException {
        if(tasks.length <= 1) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (!userInput.contains("/by")) {
            throw new DukeException("OOPS!!! Deadline must include /by (date to complete task).");
        } else if (userInput.substring(userInput.indexOf("/by") + 3).equals("") || userInput.substring(userInput.indexOf("/by") + 4).equals("")) {
            throw new DukeException("OOPS!!! Please include the date to complete task after /by command.");
        }
    }
    public static void eventCheck(String[] tasks, String userInput) throws DukeException {
        if(tasks.length <= 1) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        } else if (!userInput.contains("/at")) {
            throw new DukeException("OOPS!!! Event must include /at (time of event).");
        } else if (userInput.substring(userInput.indexOf("/at") + 3).equals("") || userInput.substring(userInput.indexOf("/at") + 4).equals("")) {
            throw new DukeException("OOPS!!! Please include the time of event after /at.");
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        while (true) {
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                int counter = 0;
                for (Task item : listOfInputs) {
                    counter++;
                    System.out.println(counter + "." + item.toString());
                }
            } else {
                String[] task = userInput.split(" ");
                String instruction = task[0];
                if(instruction.equals("done")) {
                    int taskNumber = Integer.parseInt(task[1]);
                    listOfInputs.get(taskNumber - 1).markedAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(listOfInputs.get(taskNumber - 1));
                } else if (instruction.equals("delete")) {
                    int taskNumber = Integer.parseInt(task[1]);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(listOfInputs.remove(taskNumber - 1));
                    System.out.println("Now you have " + listOfInputs.size() + " tasks in the list.");
                } else {
                    try {
                        switch(instruction) {
                            case "todo": {
                                try {
                                    todoCheck(task);
                                    Task todo = new Todo(userInput.substring(5));
                                    listOfInputs.add(todo);
                                    System.out.println("Got it. I've added this task:");
                                    System.out.println(todo);
                                    System.out.println("Now you have " + listOfInputs.size() + " tasks in the list.");
                                } catch (DukeException ex) {
                                    System.out.println(ex.getMessage());
                                } finally {
                                    break;
                                }
                            }
                            case "deadline": {
                                try {
                                    deadlineCheck(task, userInput);
                                    Task deadline = new Deadline(userInput.substring(9, userInput.indexOf("/by")), userInput.substring(userInput.indexOf("/by") + 4));
                                    listOfInputs.add(deadline);
                                    System.out.println("Got it. I've added this task:");
                                    System.out.println(deadline);
                                    System.out.println("Now you have " + listOfInputs.size() + " tasks in the list.");
                                } catch (DukeException ex) {
                                    System.out.println(ex.getMessage());
                                } finally {
                                    break;
                                }
                            }
                            case "event": {
                                try {
                                    eventCheck(task, userInput);
                                    Task event = new Event(userInput.substring(6, userInput.indexOf("/at")), userInput.substring(userInput.indexOf("/at") + 4));
                                    listOfInputs.add(event);
                                    System.out.println("Got it. I've added this task:");
                                    System.out.println(event);
                                    System.out.println("Now you have " + listOfInputs.size() + " tasks in the list.");
                                } catch (DukeException ex) {
                                    System.out.println(ex.getMessage());
                                } finally {
                                    break;
                                }
                            }
                            default: {
                                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                            }
                        }
                    } catch (DukeException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }

    }
}
