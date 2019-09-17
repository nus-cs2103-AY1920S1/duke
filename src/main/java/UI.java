import javafx.scene.control.TextField;

/**
 * Printing of the dialogues
 */
public class UI {
    private TextField userInput;

    public UI() {
    }

    String line = ("____________________________________________________\n");
    String lineOnly = ("____________________________________________________");

    public String printGreeting() {
        String logo = "   _     _      _     _      _     _       _     _      _     _      _     _      _     _   \n" +
                "  (c).-.(c)    (c).-.(c)    (c).-.(c)     (c).-.(c)    (c).-.(c)    (c).-.(c)    (c).-.(c)  \n" +
                "   / ._. \\      / ._. \\      / ._. \\       / ._. \\      / ._. \\      / ._. \\      / ._. \\   \n" +
                " __\\( Y )/__  __\\( Y )/__  __\\( Y )/__   __\\( Y )/__  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__ \n" +
                "(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._) (_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)\n" +
                "   || T ||      || A ||      || I ||       || P ||      || I ||      || N ||      || G ||   \n" +
                " _.' `-' '._  _.' `-' '._  _.' `-' '._   _.' `-' '._  _.' `-' '._  _.' `-' '._  _.' `-' '._ \n" +
                "(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.) (.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)\n" +
                " `-'     `-'  `-'     `-'  `-'     `-'   `-'     `-'  `-'     `-'  `-'     `-'  `-'     `-' ";

        String greet = line +
                "     Hello! I'm Tai Ping, your tasks manager!\n" +
                "     What can I do for you?\n" +
                lineOnly;
        return "Hello from\n" + logo + greet;
    }

    public String printBye() {
        String bye = line +
                "Bye. Hope to see you again soon! (ᵔᴥᵔ) \n" +
                line;
        return bye;
    }

    public String printIDK() {
        return line +
                "OOPS!!! I'm sorry, but I don't know what that means (ᵔᴥᵔ) \n" +
                lineOnly;
    }

    public String printList(TaskList taskList) {
        int count = 1;
        String tasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            tasks = tasks + count + "." + t.toString() + "\n";
            count++;
        }
        return (line +
                "Here are the task(s) in your list:\n" +
                tasks +
                line);
    }

    public String printDone(Task task) {
        return (line +
                "Congratulations! I've marked this task as done: \n" +
        "[" + task.getStatusIcon() + "] " + task.getDescription() + "\n" +
        line);
    }

    public String printAddTask(Task task, int taskListSize) {
        return line +
                "Got it. I've added this task: \n" +
        task.toString() + "\n" +
        "Now you have " +  taskListSize + " task(s) in the list.\n" +
        line;
    }

    public String printDelete(Task deletedTask, int taskListSize) {
        return line +
                "Noted. I've removed this task: \n" +
                deletedTask.toString() + "\n" +
                "Now you have " + (taskListSize - 1) + " task(s) in the list.\n" +
                lineOnly;
    }

    public String printFind(TaskList findList) {
        int count = 1;
        String tasks = "";
        for (int i = 0; i < findList.size(); i++) {
            Task t = findList.get(i);
            tasks = tasks + count + "." + t.toString() +"\n";
            count++;
        }
        return line +
                "Here are the matching tasks in your list:\n" +
                tasks + "\n" +
                line;

    }

    public String throwInputError(String taskType) {
        switch (taskType) {
        case "todo" :
            return (line +
                    "Hey! Description of a todo cannot be empty (ᵔᴥᵔ) \n" +
                    lineOnly);
        case "event" :
            return (line +
                    "Hey! Description of a event cannot be empty (ᵔᴥᵔ) \n" +
                    lineOnly);
        case "deadline" :
            return (line +
                    "Hey! Description of a deadline cannot be empty (ᵔᴥᵔ) \n" +
                    lineOnly);
        case "done" :
            return (line +
                    "Hey! Description of a done cannot be empty (ᵔᴥᵔ)\n" +
                    lineOnly);
        case "delete" :
            return (line +
                    "Hey! Description of a delete cannot be empty (ᵔᴥᵔ) \n" +
                    lineOnly);
        case "duplicate" :
            return (line +
                    "Hey! You are repeating your tasks (ᵔᴥᵔ) \n" +
                    lineOnly);
        default :
            return (line +
                    "OOPS!!! I'm sorry, but I don't know what that means (ᵔᴥᵔ) \n" +
                    lineOnly);
        }
    }
}
