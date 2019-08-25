import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Task> taskList = new ArrayList<>();

        String logo = "____        _        \n"
                + "\t |  _ \\ _   _| | _____ \n"
                + "\t | | | | | | | |/ / _ \\\n"
                + "\t | |_| | |_| |   <  __/\n"
                + "\t |____/ \\__,_|_|\\_\\___|\n";

        printMessage(" " + logo + "\n\t Hello! I'm Duke" + "\n" + "\t What can I do for you?");

        while(sc.hasNext()) {
            try {
                String[] inputs = sc.nextLine().split(" ");
                String name = "";
                String[] time;

                if(inputs[0].equals("bye")) {
                    printMessage("Bye. Hope to see you again soon!");
                    return;
                } else if(inputs[0].equals("list")) {
                    printTaskList(taskList);
                } else if(inputs[0].equals("done")) {

                    if(inputs.length < 2) {
                        throw new DukeException("The task Number cannot be empty.");
                    }

                    int index = Integer.parseInt(inputs[1]) - 1;

                    if(index >= taskList.size() || index < 0) {
                        throw new DukeException("Invalid task number!");
                    }

                    taskList.set(index, taskList.get(index).isDone());
                    printMessage("Nice! I've marked this task as done:"
                            + "\n\t   " + taskList.get(index).toString());
                } else if(inputs[0].equals("todo")){

                    if(inputs.length < 2) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }

                    for(int i = 1; i < inputs.length; i++) {
                        name = name + inputs[i] + " ";
                    }
                    name = name.substring(0, name.length() - 1);
                    Todo newTask = new Todo(name);
                    taskList.add(newTask);
                    printMessage("Got it. I've added this task: " +
                            "\n\t   " + newTask.toString()
                            + "\n\t Now you have " + taskList.size() + " tasks in the list.");

                } else if(inputs[0].equals("deadline") || inputs[0].equals("event")) {
                    String inputType = inputs[0];

                    if(inputs.length < 2) {
                        if(inputType.equals("deadline")) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        } else {
                            throw new DukeException("The description of an event cannot be empty.");
                        }
                    }

                    inputs = fetchString(inputs);

                    if (inputs[1].equals("")) {
                        if(inputType.equals("deadline")) {
                            throw new DukeException("The end time of a deadline cannot be empty.");
                        } else {
                            throw new DukeException("The time of an event cannot be empty.");
                        }
                    }

                    name = inputs[0].substring(0, inputs[0].length() - 1);
                    time = inputs[1].substring(0, inputs[1].length() - 1).split(" ");

                    String[] dateInString = time[0].split("\\/");

                    LocalDateTime dateAndTime = LocalDateTime.of(Integer.parseInt(dateInString[2]),
                                                    Integer.parseInt(dateInString[1]),
                                                        Integer.parseInt(dateInString[0]),
                                                            Integer.parseInt(time[1]) / 100,
                                                                    Integer.parseInt(time[1]) % 100);

                    Task newTask;

                    if (inputType.equals("deadline")) {
                        newTask = new Deadline(name, dateAndTime);
                    } else {
                        newTask = new Event(name, dateAndTime);
                    }
                    taskList.add(newTask);
                    printMessage("Got it. I've added this task: " +
                            "\n\t   " + newTask.toString()
                            + "\n\t Now you have " + taskList.size() + " tasks in the list.");
                } else if(inputs[0].equals("delete")) {
                    if(inputs.length < 2) {
                        throw new DukeException("The task Number cannot be empty.");
                    }

                    int index = Integer.parseInt(inputs[1]) - 1;

                    if(index >= taskList.size() || index < 0) {
                        throw new DukeException("Invalid task number!");
                    }

                    Task deletedTask = taskList.remove(index);

                    printMessage("Noted. I've removed this task:"
                                            + "\n\t   " + deletedTask.toString()
                                                + "\n\t Now you have "
                                                    + taskList.size()
                                                        + " tasks in the list.");


                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch(DukeException de) {
                System.err.println(de.toString() + "\n");
            }
        }
    }

    public static void printMessage(String output) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t " + output);
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    public static void printTaskList(List<Task> taskList) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Here are the tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + " " + (i + 1) + ". " + taskList.get(i));
        }
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    public static String[] fetchString(String[] arr) {
        String[] result = {"", ""};

        int index = 1;

        while(index < arr.length && !(arr[index].charAt(0) == '/')) {
            result[0] = result[0] + arr[index] + " ";
            index++;
        }

        //Skip the /at or /by
        index++;

        while(index < arr.length) {
            result[1] = result[1] + arr[index] + " ";
            index++;
        }

        return result;

    }
}


