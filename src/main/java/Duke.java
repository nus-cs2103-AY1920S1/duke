import java.util.Scanner;
import java.util.NoSuchElementException;

import com.util.Printer;

import com.tasks.DoableTask;
import com.core.State;
import com.core.Response;

public class Duke {
    public static void main(String[] args) {
        Printer.printString("Hello! I'm Duke\nWhat can I do for you?");
        // greet

        Scanner scanner = new Scanner(System.in);
        State state = new State();
        try {
            while (!state.toExit) {
                String input = scanner.nextLine();
                for (Response r : Response.values()) {
                    if (r.call(input, state)) {
                        break;
                    }
                }
            }
        } catch (NoSuchElementException ignored) {
        }

        /*ArrayList<DoableTask> list = new ArrayList<>();
        loop: while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            switch (input) {
            case "list":
                StringBuilder formattedList = new StringBuilder();
                for (int i = 0; i < list.size(); i++) {
                    if (i > 0) {
                        formattedList.append("\n");
                    }
                    formattedList.append(i + 1);
                    formattedList.append(".");
                    formattedList.append(list.get(i).toString());
                }
                // format list
                String finalString = formattedList.toString();
                finalString = finalString.equalsIgnoreCase("") ? "You have no tasks" : finalString;
                Printer.printString(finalString);
                // print list
                break;
            case "bye":
                Printer.printString("Bye. Hope to see you again soon!");
                break loop;
            // bye
            default:
                if (input.matches("^done [0-9]+$")) {
                    int index = Integer.parseInt(input.split("done ", 2)[1]) - 1;
                    // get index
                    if (index < 0 || index > list.size() - 1) {
                        Printer.printError("That is not a valid task index");
                        // index out of bounds
                    } else {
                        list.get(index).markAsDone();
                        Printer.printString(
                                "Nice! I've marked this task as done:\n  "
                                        + list.get(index).toString());
                        // mark done
                    }
                } else if (input.matches("^delete [0-9]+$")) {
                    int index = Integer.parseInt(input.split("delete ", 2)[1]) - 1;
                    // get index
                    if (index < 0 || index > list.size() - 1) {
                        Printer.printError("That is not a valid task index");
                        // index out of bounds
                    }else {
                        Printer.printString(
                                "Noted! I've removed this task:\n  " + list.get(index).toString()
                                + "\nNow you have " + (list.size() - 1) + " tasks in the list.");
                        list.remove(index);
                    }
                } else {
                    DoableTask t = null;
                    String contentString;
                    String[] contentSegments;
                    if (input.matches("^todo.*")) {
                        if (input.matches("^todo .+")) {
                            t = new Todo(input.split("todo ", 2)[1]);
                        } else {
                            Printer.printError("The description of a todo cannot be empty");
                        }
                        // add todo
                    } else if (input.matches("^deadline.*")) {
                        if (input.matches("^deadline .+ /by .+")) {
                            contentString = input.split("deadline ", 2)[1];
                            contentSegments = contentString.split("/by ");
                            t = new Deadline(contentSegments[0], contentSegments[1]);
                        } else if (input.matches("^deadline .+((?!/by ).)")) {
                            Printer.printError("The due date of a deadline cannot be empty");
                        } else {
                            Printer.printError("The description of a deadline cannot be empty");
                        }
                        // add deadline
                    } else if (input.matches("^event.*")) {
                        if (input.matches("^event .+ /at .+")) {
                            contentString = input.split("event ", 2)[1];
                            contentSegments = contentString.split("/at ");
                            t = new Event(contentSegments[0], contentSegments[1]);
                        } else if (input.matches("^event .+((?!/at ).)")) {
                            Printer.printError("The date range of an event cannot be empty");
                        } else {
                            Printer.printError("The description of an event cannot be empty");
                        }
                        // add event
                    } else {
                        Printer.printError("I'm sorry, but I don't know what that means :-(");
                    }
                    if (t != null) {
                        list.add(t);
                        Printer.addTaskMessage(t.toString(), list.size());
                    }
                    // add to list
                }
            }
        }*/
    }
}