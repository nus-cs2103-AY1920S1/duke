import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //hr is horizontal row
        String hr = "______________________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(hr);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(hr);
        ArrayList<Task> arr = new ArrayList<>();
        while(true) {
            String command = sc.nextLine();
            System.out.println(hr);
            String[] commandWords = command.split(" ");
            String order = commandWords[0];
            if (order.equals("done")){
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                try {
                    if (index >= arr.size() || index < 0) {
                        throw new DukeException(" :( OOPS!!! Requested task number is not available");
                    }
                    Task temp = arr.get(index);
                    temp.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + temp);
                    System.out.println(hr);
                } catch (DukeException de) {
                    System.err.println(de.getMessage());
                }
            } else if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(hr);
                break;
            } else if(command.equals("list")) {
                for(int i = 0; i < arr.size(); i++) {
                    Task temp = arr.get(i);
                    System.out.println((i + 1) + ". " + temp);
                }
                System.out.println(hr);
            } else {
                if (order.equals("todo")) {
                    try {
                        if (commandWords.length == 1) {
                            throw new DukeException(" :( OOPS!!! The description of a todo cannot be empty.");
                        } else {
                            Task temp = new ToDo(command.split("todo ")[1]);
                            arr.add(temp);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(" " + temp);
                            System.out.println("Now you have " + arr.size() + " tasks in the list.");
                        }
                    } catch (DukeException de) {
                        System.err.println(de.getMessage());
                    } finally {
                        System.out.println(hr);
                    }
                } else if (order.equals("deadline")) {
                    try {
                        if (commandWords.length == 1) {
                            throw new DukeException(" :( OOPS!!! The description of a deadline cannot be empty.");
                        } else {
                            if (command.split("/by").length == 1) {
                                throw new DukeException(" :( OOPS!!! A deadline must have a time.");
                            } else {
                                String instruction = command.split("deadline ")[1];
                                String[] details = instruction.split(" /by ");
                                Task temp = new Deadline(details[0], details[1]);
                                arr.add(temp);
                                System.out.println("Got it. I've added this task:");
                                System.out.println(" " + temp);
                                System.out.println("Now you have " + arr.size() + " tasks in the list.");
                            }
                        }
                    } catch (DukeException de) {
                        System.err.println(de.getMessage());
                    } finally {
                        System.out.println(hr);
                    }
                } else if (order.equals("event")){
                    try {
                        if (commandWords.length == 1) {
                            throw new DukeException(" :( OOPS!!! The description of a event cannot be empty.");
                        } else {
                            if (command.split("/at").length == 1) {
                                throw new DukeException(" :( OOPS!!! An event must have a time.");
                            } else {
                                String instruction = command.split("event ")[1];
                                String[] details = instruction.split(" /at ");
                                Task temp = new Event(details[0], details[1]);
                                arr.add(temp);
                                System.out.println("Got it. I've added this task:");
                                System.out.println(" " + temp);
                                System.out.println("Now you have " + arr.size() + " tasks in the list.");
                            }
                        }
                    } catch (DukeException de) {
                        System.err.println(de.getMessage());
                    } finally {
                        System.out.println(hr);
                    }
                } else {
                    try {
                        throw new DukeException(" :( OOPS!!! I'm sorry but I don't know what that means :-(");
                    } catch (DukeException de) {
                        System.err.println(de.getMessage());
                    } finally {
                        System.out.println(hr);
                    }
                }
            }
        }
    }
}
