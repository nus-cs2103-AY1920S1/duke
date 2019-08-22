import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> array = new ArrayList<>();

        int num = 1;

        while (sc.hasNext()) {
            String word = sc.nextLine();
            while (!word.equals("bye") && !word.equals("list") && !word.contains("done") && !word.contains("todo")
                    && !word.contains("event") && !word.contains("deadline") && !word.contains("delete")) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                word = sc.nextLine();
            }
            while(word.equals("todo") || word.equals("event") || word.equals("deadline")) {
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                word = sc.nextLine();
            }
            if (word.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
            } else if (word.equals("list")) {
                Task.printList(array);
            } else if (word.contains("done")) {
                String arr1[] = word.split(" ");
                int taskNum = Integer.parseInt(arr1[1]);
                Task.markAsDone(taskNum, array);
            } else if (word.contains("todo")) {
                Task todoT = new Todo(num, "[✗]", word, "todo");
                todoT.addList(todoT, array, num);
                num++;
            } else if (word.contains(("event"))) {
                String arr2[] = word.split("/");
                Task eventT = new Event(num, "[✗]", arr2[0], "event", arr2[1]);
                eventT.addList(eventT, array, num);
                num++;
            } else if (word.contains("deadline")) {
                String arr3[] = word.split("/");
                Task deadlineT = new Deadline(num, "[✗]", arr3[0], "deadline", arr3[1]);
                deadlineT.addList(deadlineT, array, num);
                num++;
            } else if (word.contains("delete")) {
                String arr4[] = word.split(" ");
                int taskNum = Integer.parseInt(arr4[1]);
                Task forDelete = array.get(taskNum - 1);
                forDelete.deleteTask(forDelete, array);
                int size = array.size();
                for(int i = 0; i < size; i++) {
                    Task t = array.get(i);
                    int current = t.getTaskNumber();
                    String type = t.getType();
                    if(current != i + 1) {
                        if(type.equals("todo")) {
                            Task t1 = new Todo(i + 1, t.getTaskCheck(), t.getTaskName(), "todo");
                            array.set(i, t1);
                        } else if(type.equals("event")) {
                            Task t2 = new Event(i + 1, t.getTaskCheck(), t.getTaskName(), "event", t.getAB());
                            array.set(i, t2);
                        } else {
                            Task t3 = new Deadline(i + 1, t.getTaskCheck(), t.getTaskName(), "deadline", t.getAB());
                            array.set(i, t3);
                        }
                    }
                }
            }
        }
    }
}
