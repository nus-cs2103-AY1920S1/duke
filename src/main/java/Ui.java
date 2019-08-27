import tasks.TaskList;

public class Ui {
    public void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void showAdded() {
        System.out.println("Got it. I've added this task:");
    }

    public void inputWrong() {
        System.out.println("Input format is wrong.");
    }

    public void showDeleted() {
        System.out.println("Noted. I've removed this task:");
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again!");
    }

    public void printList(TaskList t, Storage s) {
        System.out.println("Here are the tasks in your list:");
        int size = t.getCommandList().size();
        for (int i = 1; i < size + 1; i++) {
            System.out.print(i + ".");
            System.out.println(t.getCommandList().get(i - 1));
        }
    }

    public void showError(String e) {
        System.out.println(e);
    }
}
