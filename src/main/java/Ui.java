class Ui {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String intro = "Hello! I'm Duke What can I do for you?";

    static String printLogo() {
        return "Hello from\n" + logo;
    }

    static String printHello() {
        return intro;
    }

    static String printBye() {
        return "Bye. Hope to see you again soon!";
    }

    static String printList(TaskList tasks) {
        return "Here are the tasks in your list:\n" +
                tasks.printFormat();
    }

    static String printDone(Task task) {
        return "Nice! I've marked this task as done: \n" +
                "  " + task.getStatus();
    }

    static String printCount(TaskList tasks){
        return "Now you have " + tasks.size() + " tasks in the list.";
    }

    static String printCreated(Task t, TaskList tasks) {
        return "Got it. I've added this task:\n" +
                "  " + t.getStatus() +
                printCount(tasks);
    }

    static String printRemoved(Task removedTask, TaskList tasks) {
        return "Noted. I've removed this task: \n" +
                "  " + removedTask.getStatus() + "\n" +
                printCount(tasks);
    }

    static String listCommands() {
        return "Here are the following commands you can try: \n" +
                "\tlist\n" +
                "\ttodo <description>\n" +
                "\tdeadline <description> /by <memo>\n" +
                "\tevent <description> /at <memo>\n" +
                "\tdelete <task number>\n" +
                "\tdone <task number>\n" +
                "\tbye";
    }

    static String printFormatHelp() {
        return "Here are the valid formats of the inputs:\n" +
                "todo<space>description\n" +
                "\teg. todo sweep the floor\n" +
                "\t(creates a 'todo' with description 'sweep the floor'\n" +
                "deadline<space>description<space>/preposition<space>time\n" +
                "\teg. deadline assignment submission /at exam venue\n" +
                "\t(creates an 'assignment submission' deadline that is (at: exam venue)\n" +
                "event<space>description<space>/preposition<space>time\n" +
                "\teg. event happy new year /on 31/12/2019 2359\n" +
                "\t(creates a 'happy new year' event that is (on: 31 Dec 2019 2359)\n" +
                "done<space>list_index\n" +
                "\teg. done 6 (deletes Task number 6)\n" +
                "delete<space>list_index\n" +
                "\teg. delete 3 (deletes Task number 3)";
    }

    static String printFind(String input, TaskList tasks) {
        return "Here are the matching tasks in your list:\n" +
                tasks.find(input);
    }
}
