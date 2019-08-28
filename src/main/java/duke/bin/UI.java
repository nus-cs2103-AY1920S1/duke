package duke.bin;

import duke.bin.storage.DataStorage;
import duke.bin.task.Deadline;
import duke.bin.task.Event;
import duke.bin.task.Task;
import duke.bin.task.ToDo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private TaskList taskList;
    private DataStorage storage;

    public UI () {
        storage = new DataStorage("data/save.txt");
        taskList = new TaskList();
        loadDataFromStorage();
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
                    ArrayList<Task> list = taskList.getList();
                    if (list.isEmpty())
                        throw new DukeException("Oh looks like there's nothing in your list so far.");
                    wrapList(list);
                    break;

                case "done":
                    if (moreThanOne) {
                        String secondWord = words[1];
                        int index = Integer.parseInt(secondWord);
                        wrapper(taskList.markAsDone(index).toString(),
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
                        wrapper(taskList.delete(index).toString(),
                                "Noted. I've removed this task:",
                                 "Now you have " + taskList.getSize() + " tasks in the list.");
                    }
                    else {
                        throw new DukeException("I'm sorry, you didn't specify which index of the list you want to delete.");
                    }
                    break;

                case "todo":
                    if (moreThanOne) {
                        temp = new ToDo(words[1]);
                        taskList.store(temp);
                        wrapper(temp.toString(), "Got it. I've added this task:",
                                "Now you have " + taskList.getSize() + " tasks in the list.");
                    } else {
                        throw new DukeException("I'm sorry, the description of your ToDo cannot be empty.");
                    }
                    break;

                case "deadline":
                    if (moreThanOne) {
                        String[] spl = words[1].split(" /by ", 2);
                        if (spl.length <= 1) {
                            throw new DukeException("I'm sorry, you forgot to put a time for your deadline.");
                        }
                        String time = spl[1];
                        Time t = new Time(time);
                        temp = new Deadline(spl[0], t);
                        taskList.store(temp);
                        wrapper(temp.toString(), "Got it. I've added this task:",
                                "Now you have " + taskList.getSize() + " tasks in the list.");
                    } else {
                        throw new DukeException("I'm sorry, the description of your DeadLine cannot be empty.");
                    }
                    break;

                case "event":
                    if (moreThanOne) {
                    String[] split = words[1].split(" /at ", 2);
                    if (split.length <= 1) {
                        throw new DukeException("I'm sorry, you forgot to put a time for your event.");
                    }
                    String time = split[1];
                    Time t = new Time(time);
                    temp = new Event(split[0], t);
                    taskList.store(temp);
                    wrapper(temp.toString(), "Got it. I've added this task:",
                            "Now you have " + taskList.getSize() + " tasks in the list.");
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

            updateStorage();
        }
    }

    private void loadDataFromStorage() {
        try {
            taskList.store(storage.readFromFile());
        } catch (DukeException d) {
            wrapper(d.getMessage());
        } catch (IOException e) {
            wrapper(e.getMessage());
        }
    }

    private void updateStorage() {
        try {
            storage.write(taskList.getList());
        } catch (IOException e) {
            wrapper(e.getMessage());
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
