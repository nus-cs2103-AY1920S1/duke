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
        print("\tadded: "+newTask.getDescription());
    }

    public void list() {
        StringBuilder sb = new StringBuilder("\tHere are the tasks in your list:");
        for(int i = 0; i < size; i++) {
            sb.append("\n\t"+(i+1)+". ["+ myList[i].getStatusIcon()+"] " + myList[i].getDescription());
        }
        print(sb.toString());
    }

    public void markDone(int number) {
        StringBuilder sb = new StringBuilder("\tNice! I've marked this task as done: ");
        Task doneTask = myList[number-1];
        doneTask.changeStatus();
        sb.append("\n\t["+doneTask.getStatusIcon()+"] "+doneTask.getDescription());
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
        String[] splited = query.split(" ");
        while(!query.equals("bye")) {
            if(query.equals("list")) {
                duke.list();
            }else if(splited[0].equals("done")) {
                duke.markDone(Integer.parseInt(splited[1]));
            }else{
                Task task = new Task(query);
                duke.add(task);
            }
            query = sc.nextLine();
            splited = query.split(" ");
        }
        duke.exit();
    }
}
