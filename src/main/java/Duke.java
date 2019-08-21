import java.util.Scanner;

public class Duke {
    static Task[] arr = new Task[100];
    static int count = 1;

    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke\n" +
                "     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        loop:
        while (sc.hasNext()) {
            System.out.println("    ____________________________________________________________");
            String first = sc.next();
            switch (first) {
                case "bye":
                    System.out.println("     Bye. Hope to see you again soon!");
                    break loop;
                case "list":
                    System.out.println("    Here are the tasks in your list:");
                    for (Task task : arr) {
                        if (task != null) {
                            System.out.println(task.listify() + task);
                        }
                    }
                    break;
                case "done":
                    int number = Integer.parseInt(sc.next());
                    arr[number].setDone();
                    System.out.println(arr[number].doneify());
                    break;
                default:
                    String line = sc.nextLine();
                    String[] words = line.split(" ");
                    String time = "";
                    String name = "";
                    boolean timing = false;
                    boolean firstInName = true;
                    boolean firstInTime = true;
                    Task task;
                    for (String word : words) {
                        if (word.startsWith("/")) {
                            timing = true;
                        } else {
                            if (firstInName) {
                                name = word;
                                firstInName = false;
                            } else if (timing && firstInTime) {
                                time = word;
                                firstInTime = false;
                            } else if (timing) {
                                time += " " + word;
                            } else {
                                name += " " + word;
                            }
                        }
                    }
                    switch (first) {
                        case "todo":
                            task = new Todo(count, name, "T");
                            arr[count] = task;
                            count++;
                            System.out.println(task.addTask() + task);
                            break;
                        case "deadline":
                            task = new Deadline(count, name, time, "D");
                            arr[count] = task;
                            count++;
                            System.out.println(task.addTask() + task);
                            break;
                        case "event":
                            task = new Event(count, name, time, "E");
                            arr[count] = task;
                            count++;
                            System.out.println(task.addTask() + task);
                            break;
                    }
                    System.out.println("     Now you have " + (count-1) + " task(s) in the list.");
            }

            System.out.println("    ____________________________________________________________");
        }

    }
}
