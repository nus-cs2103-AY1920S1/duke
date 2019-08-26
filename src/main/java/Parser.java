public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String[] input = fullCommand.split(" ", 2);
        if (input[0].equals("bye")) {
            return new ExitCommand();
        } else if (input[0].equals("list")) {

            return new ListCommand();

        } else if (input[0].equals("done")) {

            int num = Integer.parseInt(input[1]);
            return new DoneCommand(num);

        } else if (input[0].equals("delete")) {
            int num = Integer.parseInt(input[1]);
            return new DeleteCommand(num);
        } else {

                Task t;
                //String desc = sc.nextLine();
                if (input[1].equals("")) {
                    throw new DukeException("The description of a " + input[0]+ " cannot be empty.");
                }
                switch (input[0]) {
                    case "todo":
                        t = new Todo(input[1].trim());
                        break;
                    case "deadline": {
                        String[] str = input[1].trim().split("/");
                        t = new Deadline(str[0], str[1].substring(3));
                        break;
                    }
                    case "event": {
                        String[] str = input[1].trim().split("/", 2);
                        t = new Event(str[0], str[1].substring(3));
                        break;
                    }
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
                return new AddCommand(t);

        }

    }
}
