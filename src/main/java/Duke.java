import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String saveLoadFilePath = "listSaveData.txt";

    private static ArrayList<Task> list = new ArrayList<>();

    private static void showList() {
        int counter = 0;
        for (Task item : list) {
            System.out.println(++counter + "." + item.getStatus());
        }
    }
    private static void countList() {
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    private static int getPrepositionPos(String[] input_split) {
        for (int i = 0; i < input_split.length; i++) {
            if (input_split[i].charAt(0) == '/') {
                return i;
            }
        }
        return 0;
    }

    private static Task construct(String[] input_split)
        throws EmptyDescriptionException, EmptyTimeDueException {
        if (input_split.length < 2) {
            // System.out.println("what's the " + type);
            throw new EmptyDescriptionException();
        }
        Task task;
        int prepAt = getPrepositionPos(input_split);
        StringBuilder description = new StringBuilder();
        String preposition = input_split[prepAt].substring(1);
        StringBuilder memo = new StringBuilder();
        boolean prepRequired = input_split[0].equals("deadline") || input_split[0].equals("event");

        if (prepRequired && prepAt == 0) {
            // System.out.println("what's the date due?");
            throw new EmptyTimeDueException();
        }
        if (prepRequired) {
            for (int k = 1; k < prepAt; k++) {
                description.append(" ").append(input_split[k]);
            }
        } else {
            for (int k = 1; k < input_split.length; k++) {
                description.append(" ").append(input_split[k]);
            }
        }
        for (int i = prepAt + 1; i < input_split.length; i++) {
            memo.append(" ").append(input_split[i]);
        }

        switch (input_split[0]) {
        case "todo":
            task = new ToDo(description.deleteCharAt(0).toString());
            break;
        case "deadline":
            task = new Deadline(description.deleteCharAt(0).toString(), preposition, memo.deleteCharAt(0).toString());
            break;
        case "event":
            task = new Event(description.deleteCharAt(0).toString(), preposition, memo.deleteCharAt(0).toString());
            break;
        default:
            task = null;
        }

        return task;
    }
    private static Task construct(String[] input_string, boolean completed)
            throws EmptyDescriptionException, EmptyTimeDueException{
        Task task = construct(input_string);
        task.isDone = completed;
        return task;
    }
    private static void saveList() throws IOException {
        File f = new File(saveLoadFilePath);

        // how do you work around this? it always still exists
        if (f.exists()) {
            System.out.println(f.delete() ? "Previous File deleted" : "Previous File still exists");
        }

        System.out.println(f.createNewFile() ? "New file created" : "New file not created");
        FileWriter fw = new FileWriter(f,   false);
        for (Task item : list) {
            fw.write(item.saveFormat() + "\n");
        }
        fw.close();
        System.out.println("Finished Saving");
    }
    private static void loadList() throws FileNotFoundException, EmptyDescriptionException, EmptyTimeDueException {
        File f = new File(saveLoadFilePath);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String[] task = s.nextLine().split(" ");
            boolean completed = task[0].charAt(1) == '1';
            switch(task[0].charAt(0)) {
            case 'T':
                // construct todo
                task[0] = "todo";
                break;
            case 'D':
                // construct deadline
                task[0] = "deadline";
                break;
            case 'E':
                // construct event
                task[0] = "event";
                break;
            }
            list.add(construct(task, completed));
        }
    }

    public static void main(String[] args) {
        
        try(Scanner scanner = new Scanner(System.in);
            PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8)
            ) {

            System.setOut(out);
            boolean active = true;

            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);

            try {
                loadList();
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (EmptyTimeDueException | EmptyDescriptionException e) {
                System.out.println("Error with loading: " + e.getMessage());
            }

            String intro = "Hello! I'm Duke What can I do for you?";
            System.out.println(intro);
            
            while (active && scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] input_split = input.split(" ");
                String command = input_split[0];

                try {
                    switch (command) {
                    case "exit":
                        active = false;
                        break;

                    case "bye":
                        active = false;
                        System.out.println("Bye. Hope to see you again soon!");
                        saveList();
                        break;

                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        showList();
                        break;

                    case "done":
                    if (input_split.length < 2) {
                        throw new EmptyListIndexException();
                    }
                    Task theTask = list.get(Integer.parseInt(input_split[1]) - 1);
                    theTask.isDone = true;
                    System.out.println("Nice! I've marked this task as done: \n" +
                        "  " + theTask.getStatus());
                        break;

                    case "todo":
                    case "deadline":
                    case "event":
                        Task task = construct(input_split);
                        if (task != null) {
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + task.getStatus());
                            list.add(task);
                            countList();
                        }
                        break;

                    case "delete":
                        if (input_split.length < 2) {
                            throw new EmptyListIndexException();
                        }
                        int i = Integer.parseInt(input_split[1]);
                        if (i > list.size()) throw new ListItemEmptyException();
                        Task removedTask = list.remove( i - 1);
                        System.out.println("Noted. I've removed this task: ");
                        System.out.println("  " + removedTask.getStatus());
                        countList();
                        break;

                    default:
                        throw new InputUnknownException();
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println("unable to save " + e.getMessage());
                }

            }
        }
        
    }
}
