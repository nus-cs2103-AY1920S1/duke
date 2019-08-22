public class ToDo extends Task {
    protected String by;

    public ToDo(String description) {
        super(description);
    }

    public static void addTodo(String[] words) throws DukeException {
        if (words.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String description = words[1];
        ToDo todo = new ToDo(description);
        Duke.taskList.add(todo);

        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + todo);
        Duke.printNumber();
        System.out.println("    ____________________________________________________________");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}