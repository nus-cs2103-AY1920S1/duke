import java.util.Scanner;

public class Duke {
    private Task[] myList;
    private int size;

    public Duke() {
        myList = new Task[100];
        size = 0;
        greet();
    }

    public void add(Task newTask) {
        myList[size] = newTask;
        size++;
        StringBuilder sb = new StringBuilder("\tGot it. I've added this task: ");
        sb.append("\n\t\t"+newTask);
        sb.append("\n\tNow you have "+size+" tasks in the list.");
        print(sb.toString());
    }

    public void list() {
        StringBuilder sb = new StringBuilder("\tHere are the tasks in your list:");
        for(int i = 0; i < size; i++) {
            sb.append("\n\t"+(i+1)+". "+myList[i]);
        }
        print(sb.toString());
    }

    public void markDone(int number) {
        StringBuilder sb = new StringBuilder("\tNice! I've marked this task as done: ");
        Task doneTask = myList[number-1];
        doneTask.changeStatus();
        sb.append("\n\t\t"+doneTask);
        print(sb.toString());
    }

    public void exit() {
        print("\tBye. Hope to see you again soon!");
    }

    private void print(String str) {
        System.out.println("\t____________________________________________________________");
        System.out.println(str);
        System.out.println("\t____________________________________________________________");
    }

    private void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        print("\tHello! I'm Duke\n\tWhat can I do for you?");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        String query = sc.nextLine();
        String[] parts = query.split(" ");
        String des = "";
        int index = 0;
        String date = "";
        while(!query.equals("bye")) {
            switch(parts[0]) {
                case "list":
                    duke.list();
                    break;
                case "done":
                    duke.markDone(Integer.parseInt(parts[1]));
                    break;
                case "todo":
                    des = query.substring("todo".length()+1);
                    duke.add(new Todo(des));
                    break;
                case "deadline":
                    index = query.lastIndexOf("/");
                    des = query.substring("deadline".length()+1, index-1);
                    date = query.substring(index+4);
                    duke.add(new Deadline(des, date));
                    break;
                case "event":
                    index = query.lastIndexOf("/");
                    des = query.substring("event".length()+1, index-1);
                    date = query.substring(index+4);
                    duke.add(new Event(des, date));
                    break;

            }
            query = sc.nextLine();
            parts = query.split(" ");
        }
        duke.exit();
    }
}
