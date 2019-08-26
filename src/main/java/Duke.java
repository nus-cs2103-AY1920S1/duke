import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> myList;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            myList = tasks.getTasks();
            throw new DukeException("what is this");
        } catch (DukeException | FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    private void run() {
        Scanner myScanner = new Scanner(System.in);
        ui.showWelcome();
        boolean canEnd = false;
        while (!canEnd) {
            String input;
            input = myScanner.nextLine();
            if (isDone(input) || isRemove(input)) {
                continue;
            }
            switch (input) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                canEnd = true;
                break;
            case "list":
                System.out.println("Here are the tasks in your list");
                for (int i = 0; i < myList.size(); i = i + 1) {
                    int number = i + 1;
                    System.out.println(number + "." + myList.get(i));
                }
                break;
            default:
                try {
                    processCommandType(input);
                } catch (DukeException e) {
                    System.out.println("\u2639 OOPS!!! " + e.getMessage());
                }
                break;
            }
        }
        TaskFileWriter writer = new TaskFileWriter();
        try {
            writer.writeToFile("data/duke.txt", myList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns true if input is of the form "delete <int>"
     * @param input
     */
    private boolean isRemove(String input) {
        String[] keywords = input.split(" ");
        if (keywords[0].equals("delete") && keywords.length == 2) {
            Scanner s = new Scanner(keywords[1]);
            if (s.hasNextInt()) {
                int index = s.nextInt() - 1;
                if (index >=0 && index < myList.size()) {
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(myList.get(index));
                    myList.remove(index);
                    System.out.println("Now you have " + myList.size());
                }
                else {
                    System.out.println("Please enter a valid number");
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if input is of the form "done <int>"
     * @param input
     */
    private boolean isDone(String input) {
        String[] keywords = input.split(" ");
        if (keywords[0].equals("done") && keywords.length == 2) {
            Scanner s = new Scanner(keywords[1]);
            if (s.hasNextInt()) {
                int index = s.nextInt() - 1;
                if (index >=0 && index < myList.size()) {
                    myList.get(index).setStatus(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + myList.get(index).getStatusIcon()
                            + "]" + myList.get(index).getDescription());
                }
                else {
                    System.out.println("Please enter a valid number");
                }
                return true;
            }
        }
        return false;
    }

    private void processCommandType(String input) throws DukeException {
        String[] type = new String[]{"todo", "deadline", "event"};
        String[] date = new String[]{"", "/by", "/at"};
        boolean enterLoop = false;
        for (int i = 0; i < type.length; i++) {
            int startIdx = input.indexOf(type[i]);
            int endIdx = input.indexOf(date[i]);
            if (input.trim().equals("todo")
                    || (input.split(" ")[0].equals("deadline") && endIdx == -1 && i == 1)
                    || (input.split(" ")[0].equals("event") && endIdx == -1 && i == 2)) {
                throw new DukeException(input);
            }
            if ((startIdx != -1 && i == 1)
                    || (startIdx != -1 && endIdx != -1)) {
                System.out.println("Got it. I've added this task:");
                enterLoop = true;
            } else if (i == 2 && endIdx == -1) {
                throw new DukeException(input);
            }
            if (!enterLoop) {
                continue;
            }
            try {
                if (i == 0) {
                    myList.add(new ToDo(input.substring(type[i].length())));
                } else if (i == 1) {
                    StringDateConverter converter = new StringDateConverter();
                    Date by = converter.convertStringToDate(input.substring(endIdx + date[i].length() + 1));
                    myList.add(new Deadline(input.substring(type[i].length(), endIdx), by));
                } else {
                    StringDateConverter converter = new StringDateConverter();
                    Date at = converter.convertStringToDate(input.substring(endIdx + date[i].length() + 1));
                    myList.add(new Event(input.substring(type[i].length(), endIdx), at));
                }
                System.out.println(myList.get(myList.size() - 1));
                System.out.println("Now you have " + myList.size()
                        + " tasks in the list.");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}
