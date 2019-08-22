public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static void addDeadline(String[] words) throws DukeException {
        if (words.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        String description = words[1];
        String[] actionAndTime = description.split("/by");

        Deadline deadline = new Deadline(actionAndTime[0], actionAndTime[1]);
        Duke.taskList.add(deadline);

        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + deadline);
        Duke.printNumber();
        System.out.println("    ____________________________________________________________");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
