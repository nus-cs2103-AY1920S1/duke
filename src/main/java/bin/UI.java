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
                moreThanOne = false;
            } else {
                command = input;
                moreThanOne = true;
            }

            Task temp;

            switch (command) {

            case "bye":
                wrapper("Bye. Hope to see you again soon!");
                return;

            case "list":
                wrapList(dataStorage.getList());
                break;

            case "done":
                if (moreThanOne) {
                    String secondWord = words[1];
                    int index = Integer.parseInt(secondWord);
                    wrapper(dataStorage.markAsDone(index).toString(),
                            "Nice! I've marked this task as done:");
                }
                break;

            case "todo":
                temp = new ToDo(words[1]);
                dataStorage.store(temp);
                wrapper(temp.toString(), "Got it. I've added this task:",
                        "Now you have " + dataStorage.getSize() + " tasks in the list.");
                break;

            case "deadline":
                String[] spl = words[1].split(" /by ", 2);
                temp = new Deadline(spl[0], spl[1]);
                dataStorage.store(temp);
                wrapper(temp.toString(), "Got it. I've added this task:",
                        "Now you have " + dataStorage.getSize() + " tasks in the list.");
                break;

            case "event":
                String[] split = words[1].split(" /at ", 2);
                temp = new Event(split[0], split[1]);
                dataStorage.store(temp);
                wrapper(temp.toString(), "Got it. I've added this task:",
                        "Now you have " + dataStorage.getSize() + " tasks in the list.");
                break;

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
            System.out.println(Constants.INDENTATION + i + ". " + tasks.get(i - 1) + "\n");
            i++;
        }
        System.out.println(Constants.INDENTATION + i + ". " + tasks.get(i - 1));
        System.out.println(Constants.HORIZONTAL_LINE);
    }
}
