class Ui {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String intro = "Hello! I'm Duke What can I do for you?";

    void printLogo() {
        System.out.println("Hello from\n" + logo);
    }

    void printHello() {
        System.out.println(intro);
    }

    void print(String s) {
        System.out.println(s);
    }

    void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    void printList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(tasks.printFormat());
    }

    void printDone(Task task) {
        System.out.println("Nice! I've marked this task as done: \n" +
                "  " + task.getStatus());
    }

    void printCount(TaskList tasks) {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    void printCreated(Task t, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t.getStatus());
        printCount(tasks);
    }

    void printRemoved(Task removedTask, TaskList tasks) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + removedTask.getStatus());
        printCount(tasks);
    }

    void printFormatHelp() {
        System.out.println("Here are the valid formats of the inputs:\n" +
                "todo<space>description\n" +
                "   eg. todo sweep the floor\n" +
                "   (creates a 'todo' with description 'sweep the floor'\n" +
                "deadline<space>description<space>/preposition<space>time\n" +
                "   eg. deadline assignment submission /at exam venue\n" +
                "   (creates an 'assignment submission' deadline that is (at: exam venue)\n" +
                "event<space>description<space>/preposition<space>time\n" +
                "   eg. event happy new year /on 31/12/2019 2359\n" +
                "   (creates a 'happy new year' event that is (on: 31 Dec 2019 2359)\n" +
                "done<space>list_index\n" +
                "   eg. done 6 (deletes Task number 6)\n" +
                "delete<space>list_index\n" +
                "   eg. delete 3 (deletes Task number 3)");
    }
}
