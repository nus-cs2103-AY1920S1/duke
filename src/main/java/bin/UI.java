package bin;

import bin.task.Deadline;
import bin.task.Event;
import bin.task.Task;
import bin.task.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private DataStorage dataStorage;

    public UI () {
        dataStorage = new DataStorage();
    }

    public void run () {
        String greeting = Constants.HORIZONTAL_LINE + Constants.INDENTATION + "Hello! I'm Duke\n"
                + Constants.INDENTATION + "What can I do for you?\n" + Constants.HORIZONTAL_LINE;
        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] words = input.split(" ", 2);
            String command;
            boolean moreThanOne;
            if (input.contains(" ")) {
                command = words[0];
                moreThanOne = true;
            } else {
                command = input;
                moreThanOne = false;
            }

            Task temp;
            try {
                switch(command) {

                case "bye":
                    wrapper("Bye. Hope to see you again soon!");
                    return;

                case "list":
                    ArrayList<Task> list = dataStorage.getList();
                    if (list.isEmpty())
                        throw new DukeException("Oh looks like there's nothing in your list so far.");
                    wrapList(list);
                    break;

                case "done":
                    if (moreThanOne) {
                        String secondWord = words[1];
                        int index = Integer.parseInt(secondWord);
                        wrapper(dataStorage.markAsDone(index).toString(),
                                "Nice! I've marked this task as done:");
                    }
                    else {
                        throw new DukeException("I'm sorry, you didn't specify which index of the list you've done.");
                    }
                    break;

                case "delete":
                    if (moreThanOne) {
                        String secondWord = words[1];
                        int index = Integer.parseInt(secondWord);
                        wrapper(dataStorage.delete(index).toString(),
                                "Noted. I've removed this task:",
                                 "Now you have " + dataStorage.getSize() + " tasks in the list.");
                    }
                    else {
                        throw new DukeException("I'm sorry, you didn't specify which index of the list you want to delete.");
                    }
                    break;

                case "todo":
                    if (moreThanOne) {
                        temp = new ToDo(words[1]);
                        dataStorage.store(temp);
                        wrapper(temp.toString(), "Got it. I've added this task:",
                                "Now you have " + dataStorage.getSize() + " tasks in the list.");
                    } else {
                        throw new DukeException("I'm sorry, the description of your ToDo cannot be empty.");
                    }
                    break;

                case "deadline":
                    if (moreThanOne) {
                        String[] spl = words[1].split(" /by ", 2);
                        String time = spl.length > 1 ? spl[1]: "NA";
                        temp = new Deadline(spl[0], time);
                        dataStorage.store(temp);
                        wrapper(temp.toString(), "Got it. I've added this task:",
                                "Now you have " + dataStorage.getSize() + " tasks in the list.");
                    } else {
                        throw new DukeException("I'm sorry, the description of your DeadLine cannot be empty.");
                    }
                    break;

                case "event":
                    if (moreThanOne) {
                    String[] split = words[1].split(" /at ", 2);
                    String time = split.length > 1 ? split[1]: "NA";
                    temp = new Event(split[0], time);
                    dataStorage.store(temp);
                    wrapper(temp.toString(), "Got it. I've added this task:",
                            "Now you have " + dataStorage.getSize() + " tasks in the list.");
                    } else {
                        throw new DukeException("I'm sorry, the description of your Event cannot be empty.");
                    }
                    break;

                default:
                    throw new DukeException("I'm sorry, but I don't know what that means :(");
                }
            } catch (DukeException d) {
                wrapper(d.getMessage());
            } catch (NumberFormatException e) {
                wrapper("I'm sorry please give a number instead.");
            }
        }
    }

    private void wrapper(String string) {
        System.out.println(Constants.HORIZONTAL_LINE + Constants.INDENTATION + string + "\n" + Constants.HORIZONTAL_LINE);
    }

    private void wrapper(String string, String startPhrase) {
        System.out.println(Constants.HORIZONTAL_LINE + Constants.INDENTATION + startPhrase);
        System.out.println(Constants.INDENTATION + string + "\n" + Constants.HORIZONTAL_LINE);
    }

    private void wrapper(String string, String startPhrase, String endingPhrase) {
        System.out.println(Constants.HORIZONTAL_LINE + Constants.INDENTATION + startPhrase);
        System.out.println(Constants.INDENTATION + "  " + string);
        System.out.println(Constants.INDENTATION + endingPhrase + "\n" + Constants.HORIZONTAL_LINE);
    }

    private void wrapList(ArrayList<Task> tasks) {
        System.out.println(Constants.HORIZONTAL_LINE + Constants.INDENTATION + "Here are the tasks in your list:");
        int i = 1;
        while( i < tasks.size()) {
            System.out.println(Constants.INDENTATION + i + ". " + tasks.get(i - 1));
            i++;
        }
        System.out.println(Constants.INDENTATION + i + ". " + tasks.get(i - 1));
        System.out.println(Constants.HORIZONTAL_LINE);
    }
}
