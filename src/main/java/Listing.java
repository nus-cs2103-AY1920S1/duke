import java.util.ArrayList;

public class Listing {
    private ArrayList<Task> list;

    public Listing() {
        this.list = new ArrayList<>();
    }

    public void add(String msg) {
        Task task = new Task(msg);
        list.add(task);
        System.out.println(String.format("added: %s", msg));
    }

    //msg[0] = event, msg[1] = description
    public void addTodo(String[] msg) throws DukeException {
        if(msg.length > 1 && !msg[1].equals("")) {
            Task task = new Todo(msg[1]);
            list.add(task);
            System.out.println(String.format(
                "Got it. I\'ve added this task:\n  "
                + task + "\nNow you have %s tasks in the list", Task.getNoOfTask()));
        }else {
            throw new DukeException("\u1F65 OOPS!!! The description of a todo cannot be empty.");
        }
    }

    //msg[0] = event, msg[1] = description
    public void addDeadline(String[] msg) throws DukeException {
        if(msg.length > 1) {
            String[] msgs = msg[1].split("/by");
            //check is the description correct.
            if(msgs.length == 2 && !msgs[1].equals(" ")) {
                Task task = new Deadline(msgs[0], msgs[1]);
                list.add(task);
                System.out.println(String.format(
                        "Got it. I\'ve added this task:\n  "
                        + task + "\nNow you have %s tasks in the list", Task.getNoOfTask()));
            }else {
                throw new DukeException("\u1F65 OOPS!!! The format of the description of a deadline is wrong");

            }
        }else {
            throw new DukeException("\u1F65 OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    //msg[0] = event, msg[1] = description
    public void addEvent (String[] msg) throws DukeException {
        if(msg.length > 1) {
            String[] msgs = msg[1].split("/at");
            if(msgs.length == 2 && !msgs[1].equals(" ")) {
                Task task = new Event(msgs[0], msgs[1]);
                list.add(task);
                System.out.println(String.format(
                        "Got it. I\'ve added this task:\n  "
                                + task + "\nNow you have %s tasks in the list", Task.getNoOfTask()));
            }else {
                throw new DukeException("\u1F65 OOPS!! The format of the description of a deadline is wrong.");
            }
        }else {
            throw new DukeException("\u1F65 OOPS!! The description of a deadline cannot be empty.");
        }
    }

    //when the input is invalid
    public void invalidInput() throws DukeException {
        throw new DukeException("\u1F65 OOPS!! I\'m sorry, but I don\'t know what that means :-(");
    }


    public void mark(String[] msg) throws  DukeException {
        if(msg.length == 2) {
            try {
                int index = Integer.parseInt(msg[1]);
                Task tk = list.get(index - 1);
                tk.markAsDone();
                System.out.println(
                        String.format("Nice! I've marked this task as done:\n"
                                + "  " + tk));
            } catch (NumberFormatException ex) {
                throw new DukeException("\u1F65 OOPS! Invalid input as number");
            }
        } else {
            throw new DukeException("\u1F65 OOPS!! The format of the input is wrong");
        }
    }

    public void listing(String[] msg) throws DukeException {
        if(msg.length == 1) {
            for (int i = 0; i < list.size(); i++) {
                Task tk = list.get(i);
                System.out.println(i + 1 + "." + tk);
            }
        } else {
            throw new DukeException("\u1F65 OOPS!! I\'m sorry, but I don\'t know what that means :-(");
        }
    }
}
