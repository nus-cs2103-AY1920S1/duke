import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static String horizontal_line = "\t____________________________________________________________";

    public static void main(String[] args) {
        //start Duke program
        Scanner sc = new Scanner(System.in);
        System.out.println(horizontal_line);
        System.out.println("\t  Hello! I'm Duke");
        System.out.println("\t  What can I do for you?");
        System.out.println(horizontal_line);

        //store the list
        ArrayList<Task> strArr = new ArrayList<>();

        //take in inputs
        while (sc.hasNext()) {
            try {
                String inputStr = sc.nextLine();
                String[] inputArr = inputStr.split(" ");

                if (inputArr[0].equals("bye")) {
                    System.out.println(horizontal_line);
                    System.out.println("\t  Bye. Hope to see you again soon!");
                    System.out.println(horizontal_line);
                    break;
                } else if (inputArr[0].equals("list")) {
                    System.out.println(horizontal_line);
                    System.out.println("\t  Here are the tasks in your list:");
                    for (int i = 0; i < strArr.size(); i++) {
                        System.out.println("\t  " + (i + 1) + ". " + strArr.get(i));
                    }
                    System.out.println(horizontal_line);
                } else if (inputArr[0].equals("done")) {
                    Integer indexDone = Integer.valueOf(inputArr[1]);
                    strArr.get(indexDone - 1).markAsDone();

                    System.out.println(horizontal_line);
                    System.out.println("\t  Nice! I've marked this task as done:");
                    System.out.println("\t    " + strArr.get(indexDone - 1));
                    System.out.println(horizontal_line);
                } else if (inputArr[0].equals("todo")) {
                    // todo event
                    if (inputArr.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }

                    StringBuilder newStr = new StringBuilder();
                    for (int x = 1; x < inputArr.length; x++) {
                        if (x == inputArr.length - 1) {
                            newStr.append(inputArr[x]);
                        } else {
                            newStr.append(inputArr[x] + " ");
                        }
                    }

                    Task todoTask = new Todo(newStr.toString());
                    strArr.add(todoTask);
                    printAddedTask(todoTask, strArr.size());
                } else if (inputArr[0].equals("deadline")) {
                    // deadline
                    StringBuilder desc = new StringBuilder();
                    StringBuilder by = new StringBuilder();
                    for (int x = 1; x < inputArr.length; x++) {
                        if (inputArr[x].charAt(0) == '/') {
                            for (int xx = x + 1; xx < inputArr.length; xx++) {
                                if (xx == inputArr.length - 1) {
                                    by.append(inputArr[xx]);
                                } else {
                                    by.append(inputArr[xx] + " ");
                                }
                            }
                            break;
                        } else {
                            if (x == inputArr.length - 1) {
                                desc.append(inputArr[x]);
                            } else {
                                desc.append(inputArr[x] + " ");
                            }
                        }
                    }

                    Task deadlineTask = new Deadline(desc.toString(), by.toString());
                    strArr.add(deadlineTask);
                    printAddedTask(deadlineTask, strArr.size());
                } else if (inputArr[0].equals("event")) {
                    // event
                    StringBuilder desc = new StringBuilder();
                    StringBuilder at = new StringBuilder();
                    for (int x = 1; x < inputArr.length; x++) {
                        if (inputArr[x].charAt(0) == '/') {
                            for (int xx = x + 1; xx < inputArr.length; xx++) {
                                if (xx == inputArr.length - 1) {
                                    at.append(inputArr[xx]);
                                } else {
                                    at.append(inputArr[xx] + " ");
                                }
                            }
                            break;
                        } else {
                            if (x == inputArr.length - 1) {
                                desc.append(inputArr[x]);
                            } else {
                                desc.append(inputArr[x] + " ");
                            }
                        }
                    }

                    Task eventTask = new Event(desc.toString(), at.toString());
                    strArr.add(eventTask);
                    printAddedTask(eventTask, strArr.size());
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException d) {
                System.err.println(d);
            }
        }
    }

    public static void printAddedTask(Task taskAdded, int totalTask) {
        System.out.println(horizontal_line);
        System.out.println("\t  Got it. I've added this task:");
        System.out.println("\t    " + taskAdded);
        System.out.println("\t  Now you have " + totalTask +" tasks in the list.");
        System.out.println(horizontal_line);
    }
}