public class Ui {

    public Ui(){}

    private final String horizontalLine = "____________________________________________________________";

    public void load() {
        System.out.println("\t" + horizontalLine);
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println("\t" + horizontalLine);
    }

    public void addTask(String instruction, Task task, int numberOfTaskInList) {
        System.out.println(instruction);
        System.out.println("\t" + horizontalLine);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task.toString());
        System.out.println("\tNow you have " + numberOfTaskInList + " tasks in the list.");
        System.out.println("\t" + horizontalLine);
    }

    public void list(List<Task> listOfTask) {
        System.out.println("list");
        System.out.println("\t" + horizontalLine);
        System.out.println("\tHere are the tasks in your list:");
        int count = 0;
        for (Task task: listOfTask) {
            count++;
            System.out.println(count + "." + task.toString());
        }
        System.out.println("\t" + horizontalLine);
    }

    public void done(Task task) {
        System.out.println("done");
        System.out.println("\t" + horizontalLine);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + task.toString());
        System.out.println("\t" + horizontalLine);
    }

    public void bye() {
        System.out.println("bye");
        System.out.println("\t" + horizontalLine);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t" + horizontalLine);
    }

    public void showLoadingError() {
        System.out.println("Failed to load. :( ");
    }

}