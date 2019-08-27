public class Command {
    private String input;

    public Command(String input) {
        this.input = input;
    }

    public void execute(Ui ui, TaskList t,Storage s) throws DukeException {
        if (input.length() <= 3) {
            throw new IncorrectInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else {
            if (input.equals("list")) {
                ui.printList(t);
            } else if (input.substring(0, 4).equals("done")) {
                Parser.parseDone(input,t,s);
            } else {
                if (input.substring(0, 4).equals("todo")) {
                    Parser.parseToDo(input,t,ui,s);
                } else if (input.length() <= 5) {
                    throw new IncorrectInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                } else if (input.substring(0, 5).equals("event")) {
                    Parser.parseEvent(input,t,ui,s);
                } else if (input.substring(0, 6).equals("delete")) {
                    Parser.parseDelete(input,t,ui,s);
                } else if (input.length() <= 7) {
                    throw new IncorrectInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                } else if (input.substring(0, 8).equals("deadline")) {
                    Parser.parseDeadline(input,t,ui,s);
                } else {
                    throw new IncorrectInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                int numberOfTasks = t.getCommandList().size();
                if (numberOfTasks == 1) {
                    System.out.println("Now you have " + numberOfTasks + " task in the list");
                } else
                    System.out.println("Now you have " + numberOfTasks + " tasks in the list");
            }
        }
    }
}
