import java.util.ArrayList;

public class Parser {
    private boolean valid = true;
    private ArrayList<String> list = new ArrayList<>();
    private String type;

    public Parser(String command) throws DukeException{
        String[] commandWords = command.split(" ");
        String order = commandWords[0];
        if (command.split(order  + " ").length == 1 &&
                (order.equals("event") || order.equals("todo") || order.equals("deadline"))) {
            throw new DukeException(" :( OOPS!!! The description of " + order + "s cannot be empty.");
        }
        if (order.equals("todo")) {
            type = "todo";
            if (command.split("todo ").length > 1) {
                list.add(command.split("todo ")[1]);
            }
        } else if (order.equals("event")) {
            if (command.split("/at").length == 1) {
                throw new DukeException(" :( OOPS!!! An event must have a start and end time. Use '/at'.");
            }
            type = "event";
            String instruction = command.split("event ")[1];
            String[] details = instruction.split(" /at ");
            String[] dateAndTime = details[1].split(" ");
            String[] dateArray = dateAndTime[0].split("/");
            int startTime = Integer.parseInt(dateAndTime[1].split("-")[0]);
            int endTime = Integer.parseInt(dateAndTime[1].split("-")[1]);
            int startHours = startTime / 100;
            int startMinutes = startTime % 100;
            int endHours = endTime / 100;
            int endMinutes = endTime % 100;
            list.add(details[0]);
            list.add(dateArray[0]);
            list.add(dateArray[1]);
            list.add(dateArray[2]);
            list.add("" + startHours);
            list.add("" + startMinutes);
            list.add("" + endHours);
            list.add("" + endMinutes);
        } else if (order.equals("deadline")) {
            type = "deadline";
            if (command.split("/by").length == 1) {
                throw new DukeException(" :( OOPS!!! An deadline must have a time. Use '/by'.");
            }
            String instruction = command.split("deadline ")[1];
            String[] details = instruction.split(" /by ");
            String[] dateAndTime = details[1].split(" ");
            String[] dateArray = dateAndTime[0].split("/");
            int time = Integer.parseInt(dateAndTime[1]);
            int hours = time / 100;
            int minutes = time % 100;
            list.add(details[0]);
            list.add(dateArray[0]);
            list.add(dateArray[1]);
            list.add(dateArray[2]);
            list.add("" + hours);
            list.add("" + minutes);
        } else if (order.equals("done") || order.equals("delete")) {
            type = order;
            list.add(command.split(order + " ")[1]);
        } else if (order.equals("list") || order.equals("bye")) {
            type = order;
        } else {
            valid = false;
        }
    }

    public String getType() {
        return type;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public boolean isValid() {
        return valid;
    }
}
