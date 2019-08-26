public class UI {

    public UI() {}

    public void horizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public void greet() {
        horizontalLine();
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        horizontalLine();
        System.out.println();
    }

    public void exit() {
        horizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        horizontalLine();
    }

    public void printList(TaskList taskList) {
        horizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println(i+1 + ".[" + taskList.getTask(i).getType() + "]"+ "[" + taskList.getTask(i).getStatusIcon() + "] " + taskList.getTask(i).getDescription() + taskList.getTask(i).getDate());
        }
        horizontalLine();
        System.out.println();
    }


}
