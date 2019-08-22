import java.util.Scanner;
import java.util.ArrayList;

class ToDo {
    private ArrayList<Task> todoList = new ArrayList<>();

    ToDo() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    void run() {
        Scanner sc = new Scanner(System.in);
        String nextCommand = sc.nextLine();
        while (!nextCommand.equals("bye")) {
            String[] splitCommand = nextCommand.split(" ");
            if (splitCommand.length == 1 && splitCommand[0].equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < todoList.size(); i++) {
                    System.out.println(i + 1 + "." + todoList.get(i));
                }
                nextCommand = sc.nextLine();
            } else if (splitCommand[0].equals("done")) {
                int index = Integer.parseInt(splitCommand[1]) - 1;
                todoList.get(index).markAsDone();
                nextCommand = sc.nextLine();
            } else if (splitCommand[0].equals("event")) {
                if (splitCommand.length < 4) {
                    throwError(splitCommand[0]);
                } else {
                    String event = splitCommand[1];
                    int indexStop = 0;
                    for (int i = 2; i < splitCommand.length; i++) {
                        if (splitCommand[i].equals("/at")) {
                            indexStop = i + 1;
                            break;
                        }
                        event += " " + splitCommand[i];
                    }
                    String time = splitCommand[indexStop];
                    for (int i = indexStop + 1; i < splitCommand.length; i++) {
                        time += " " + splitCommand[i];
                    }
                    Task newTask = new Event(event, time);
                    todoList.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + todoList.size() + " tasks in the list.");
                }
                nextCommand = sc.nextLine();
            } else if (splitCommand[0].equals("deadline")) {
                if (splitCommand.length < 4) {
                    throwError(splitCommand[0]);
                } else {
                    String event = splitCommand[1];
                    int indexStop = 0;
                    for (int i = 2; i < splitCommand.length; i++) {
                        if (splitCommand[i].equals("/by")) {
                            indexStop = i + 1;
                            break;
                        }
                        event += " " + splitCommand[i];
                    }
                    String time = splitCommand[indexStop];
                    for (int i = indexStop + 1; i < splitCommand.length; i++) {
                        time += " " + splitCommand[i];
                    }
                    Task newTask = new Event(event, time);
                    todoList.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + todoList.size() + " tasks in the list.");
                }
                nextCommand = sc.nextLine();
            } else if (splitCommand[0].equals("todo")) {
                if (splitCommand.length < 2) {
                    throwError(splitCommand[0]);
                } else {
                    String task = splitCommand[1];
                    if (splitCommand.length > 2) {
                        for (int i = 2; i < splitCommand.length; i++) {
                            task += " " + splitCommand[i];
                        }
                    }
                    Task newTask = new TodoTask(task);
                    todoList.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + todoList.size() + " tasks in the list.");
                }
                nextCommand = sc.nextLine();
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                nextCommand = sc.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void throwError(String task) {
        System.out.println("☹ OOPS!!! The description of a " + task + " cannot be empty.");
    }
}
