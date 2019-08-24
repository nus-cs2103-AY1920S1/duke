import java.util.*;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        String command;
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        while (!(command = sc.nextLine()).equals("bye")) {
            try {
                if (command.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println((i + 1) + "." + taskList.get(i).toString());
                    }
                } else {
                    String[] commandSplit = command.split(" ");
                    String firstInput = commandSplit[0];
                    switch (firstInput) {
                        case "done":
                            if (commandSplit.length == 1) {
                                throw new DukeException("☹ OOPS!!! Please indicate which task you have completed.");
                            } else {
                                String secondInput = commandSplit[1];
                                int numChange = Integer.parseInt(secondInput);
                                if (numChange > taskList.size()) {
                                    throw new DukeException("☹ OOPS!!! Task " + numChange + " does not exist.");
                                } else {
                                    taskList.get(numChange - 1).markAsDone();
                                    System.out.println("Nice! I've marked this task as done:" + "\n" +
                                            taskList.get(numChange - 1).toString());
                                }
                            }
                            break;

                        case "delete":
                            if (commandSplit.length == 1) {
                                throw new DukeException("☹ OOPS!!! Please indicate which task you would like to delete.");
                            } else {
                                String secondInput = commandSplit[1];
                                int numChange = Integer.parseInt(secondInput);
                                if (numChange > taskList.size()) {
                                    throw new DukeException("☹ OOPS!!! Task " + numChange + " does not exist.");
                                } else {
                                    Task toRemove = taskList.get(numChange - 1);
                                    taskList.remove(numChange - 1);
                                    System.out.println("Noted. I've removed this task:" + "\n" + toRemove.toString()
                                            + "\n" + "Now you have " + taskList.size() + " tasks in the list.");
                                }
                            }
                            break;

                        case "todo":
                        case "deadline":
                        case "event":
                            if (commandSplit.length == 1) {
                                throw new DukeException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
                            }
                            Type enumType = Type.valueOf(firstInput.toUpperCase());

                            switch (enumType) {
                                case TODO: {
                                    Task newTask = new Todo(command.substring(5));
                                    taskList.add(newTask);
                                    int numTasks = taskList.size();
                                    System.out.println("Got it. I've added this task:" + "\n" + newTask.toString()
                                            + "\n" + "Now you have " + numTasks + " tasks in the list.");
                                }
                                    break;

                                case DEADLINE: {
                                    String[] timeSplit = command.split("/");
                                    if (timeSplit.length == 1) {
                                        throw new DukeException(" ☹ OOPS!!! Please enter a deadline for your task.");
                                    } else {
                                        Task newTask = new Deadline(timeSplit[0].substring(9), timeSplit[1].substring(3));
                                        taskList.add(newTask);
                                        int numTasks = taskList.size();
                                        System.out.println("Got it. I've added this task:" + "\n" + newTask.toString()
                                                + "\n" + "Now you have " + numTasks + " tasks in the list.");
                                    }
                                }
                                    break;

                                case EVENT:
                                    String[] timeSplit = command.split("/");
                                    if (timeSplit.length == 1) {
                                        throw new DukeException(" ☹ OOPS!!! Please enter a time for your task.");
                                    } else {
                                        Task newTask = new Events(timeSplit[0].substring(6), timeSplit[1].substring(3));
                                        taskList.add(newTask);
                                        int numTasks = taskList.size();
                                        System.out.println("Got it. I've added this task:" + "\n" + newTask.toString()
                                                + "\n" + "Now you have " + numTasks + " tasks in the list.");
                                    }
                                    break;
                            }
                            break;

                        default:
                            throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                    try {
                        FileWriter fw = new FileWriter("C:\\Users\\Lynn\\Desktop\\Y2S1\\CS2103T\\dukenew\\src\\main\\java\\duke.txt");
                        for (int i=0; i<taskList.size(); i++) {
                            fw.write(taskList.get(i).toFileFormat());
                        }

                        fw.close();
                    } catch (IOException e) {
                        throw new DukeException("Something went wrong writing to file!");
                    }

                }
            } catch(DukeException ex){
                    System.out.println(ex.getMessage());
                }
            }
            System.out.println("Bye. Hope to see you again soon!");
        }
    }



