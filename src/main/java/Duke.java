import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String command;
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        while(!(command = sc.nextLine()).equals("bye")) {
            try {
                if(command.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for(int i=0; i<taskList.size(); i++) {
                        System.out.println((i+1) + "." + taskList.get(i).toString());
                    }
                } else if((command.split(" ")[0]).equals("done")) {
                    int numChange = Integer.parseInt(command.split(" ")[1]) - 1;
                    taskList.get(numChange).markAsDone();
                    System.out.println("Nice! I've marked this task as done:" + "\n" +
                            taskList.get(numChange).toString());
                } else if((command.split(" ")[0]).equals("todo")||(command.split(" ")[0]).equals("deadline")||(command.split(" ")[0]).equals("event")) {
                    String type = command.split(" ")[0];

                    if(command.split(" ").length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
                    } else {
                        if(type.equals("todo")) {
                            Task newTask = new Todo(command.substring(5));
                            taskList.add(newTask);
                            int numTasks = taskList.size();
                            System.out.println("Got it. I've added this task:" + "\n" + newTask.toString()
                                    + "\n" + "Now you have " + numTasks + " tasks in the list.");
                        } else if(type.equals("deadline")) {
                            String[] commandSplit= command.split("/");
                            Task newTask = new Deadline(commandSplit[0].substring(9), commandSplit[1].substring(3));
                            taskList.add(newTask);
                            int numTasks = taskList.size();
                            System.out.println("Got it. I've added this task:" + "\n" + newTask.toString()
                                    + "\n" + "Now you have " + numTasks + " tasks in the list.");
                        } else {
                            String[] commandSplit= command.split("/");
                            Task newTask = new Events(commandSplit[0].substring(6), commandSplit[1].substring(3));
                            taskList.add(newTask);
                            int numTasks = taskList.size();
                            System.out.println("Got it. I've added this task:" + "\n" + newTask.toString()
                                    + "\n" + "Now you have " + numTasks + " tasks in the list.");
                        }

                    }

                } else {
                    throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

            } catch (DukeException ex){
                System.out.println(ex.getMessage());
            }

        }

        System.out.println("Bye. Hope to see you again soon!");
    }


}


