import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

    private Storage storage;
    private TaskList tasks;

    public Parser (Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
    }

    public void read (String command, Scanner sc) {
        System.out.println(command.equals("deadline"));
        if (command.equals("done")) {

            try {
                int num = sc.nextInt();
                tasks.taskDone(num-1);
                System.out.println("Nice! I've marked this task as done: \n" + tasks.taskPrint(num-1));
                storage.append(tasks.getLast());
            } catch(Exception e) {
                System.out.println("Error, you have entered an invalid number");
            }
        } else if(command.equals("delete")) {
            try {
                int deleteNum = sc.nextInt();
                System.out.println("Noted. I've removed this task: ");
                System.out.println(tasks.taskPrint(deleteNum -1));
                System.out.println("Now you have " + (tasks.size() -1) + " tasks in the list. ");
                tasks.remove(deleteNum-1);
                storage.append(tasks.getLast());
            } catch(Exception e) {
                System.out.println("Error, you have entered an invalid number");
            }
        } else if(command.equals("list")) {
            tasks.printForList();
        } else {
            try {
                if (command.equals("todo")) {
                    String descToDo = sc.nextLine();
                    if (!descToDo.isEmpty()) {
                        tasks.add(new Todo(descToDo));
                        storage.append(tasks.getLast());
                    } else {
                        throw new Exception();
                    }
                } else if (command.equals("deadline")) {
                    String rem = sc.nextLine();
                    if (!rem.isEmpty()) {
                        String[] descriptionNDate = rem.split("/by");
                        String description = descriptionNDate[0];
                        String by = descriptionNDate[1];
                        tasks.add(new Deadline(description, by));
                        storage.append(tasks.getLast());
                    } else {
                        throw new Exception();
                    }
                } else if (command.equals("event")) {
                    String remDetails = sc.nextLine();
                    if (!remDetails.isEmpty()) {
                        String[] descriptionNAt = remDetails.split("/at");
                        String desc = descriptionNAt[0];
                        String at = descriptionNAt[1];
                        tasks.add(new Event(desc, at));
                        storage.append(tasks.getLast());
                    } else {
                        throw new Exception();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
                System.out.println("Got it. I've added this task: ");
                System.out.println(" " + tasks.printLatest());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } catch(IllegalArgumentException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch(Exception e) {
                System.out.println(command);
                System.out.println("OOPS!!! The description of a todo cannot be empty.");
            }
        }
    }
}
