import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Task> list = new ArrayList<>();

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            System.out.println(input);
            System.out.println("____________________________________________________________");
            String[] words = input.split(" ");
            StringBuilder description = new StringBuilder();

            switch(words[0]) {

                case "todo":
                    if (words.length <= 1) {
                        System.out.println(new DukeException());
                        break;
                    }
                    for (int i = 1; i < words.length; i++) {
                        description.append(words[i]);
                    }
                    Task td = new ToDo(description.toString());
                    list.add(td);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + td.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    break;

                case "event":
                    if (words.length <= 1) {
                        System.out.println(new DukeException());
                        break;
                    }
                    StringBuilder at = new StringBuilder();
                    for (int i = 1; i < words.length; i++) {
                        if (words[i].equals("/at")) {
                            for (int j = i + 1; j < words.length; j++) {
                                at.append(words[j]);
                            }
                            break;
                        } else {
                            description.append(words[i]);
                        }
                    }
                    Task e = new Event(description.toString(), at.toString());
                    list.add(e);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + e.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    break;

                case "deadline":
                    if (words.length <= 1) {
                        System.out.println(new DukeException());
                        break;
                    }
                    StringBuilder by = new StringBuilder();
                    for (int i = 1; i < words.length; i++) {
                        if (words[i].equals("/by")) {
                            for (int j = i + 1; j < words.length; j++) {
                                by.append(words[j]);
                            }
                            break;
                        } else {
                            description.append(words[i]);
                        }
                    }
                    Task d = new Deadline(description.toString(), by.toString());
                    list.add(d);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + d.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    break;

                case "done":
                    int index = Integer.parseInt(words[1]);
                    Task t = list.get(index - 1);
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + t.toString());
                    break;

                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;

                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= list.size(); i++) {
                        System.out.println(i + "." + list.get(i-1).toString());
                    }
                    break;

                default:
                    System.out.println(new DukeException());
                    break;
            }
            System.out.println("____________________________________________________________");

        }
    }
}
