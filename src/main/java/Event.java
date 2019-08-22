public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public static void addEvent(String[] words) throws DukeException {
        if (words.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        String description = words[1];
        String[] actionAndTime = description.split("/at");
        Event event = new Event(actionAndTime[0], actionAndTime[1]);
        Duke.taskList.add(event);

        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + event);
        Duke.printNumber();
        System.out.println("    ____________________________________________________________");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }
}